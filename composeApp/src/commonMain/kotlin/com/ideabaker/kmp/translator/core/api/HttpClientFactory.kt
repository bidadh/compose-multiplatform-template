package com.ideabaker.kmp.translator.core.api

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import com.ideabaker.kmp.translator.core.token.TokenRepository
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.SendingRequest
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.AttributeKey
import io.ktor.util.appendIfNameAbsent
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

class HttpClientFactory(
  private val repo: TokenRepository,
  private val clock: Clock,
  private val log: Logger,
  private val baseUrl: String
) {
  fun create(): HttpClient {
    val httpClient = HttpClient(engine) {
      configureEngin()
      ResponseObserver
      HttpResponseValidator {
        validateResponse { response ->
          val statusCode = response.status.value
          if (statusCode in 200..299) {
            return@validateResponse
          }

          val bodyAsText = response.bodyAsText()
          when (statusCode) {
            in 401 .. 403 -> {
              log.d { "Unauthorized; $bodyAsText" }
              throw ServerException(ServerError.UNAUTHORIZED, "Unauthorized")
            }
            500 -> throw ServerException(ServerError.SERVER_ERROR, response.bodyAsText())
            400 -> throw ServerException(ServerError.BAD_REQUEST, bodyAsText)
            404 -> throw ServerException(ServerError.NOT_FOUND, bodyAsText)
            409 -> throw ServerException(ServerError.DUPLICATE_ENTITY, bodyAsText)
            else -> throw ServerException(ServerError.UNKNOWN_ERROR, bodyAsText)
          }
        }
      }
      install(ContentNegotiation) {
        json(
          Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
            prettyPrint = true
          }
        )
      }
      install(Auth)
      install(HttpCache)
      install(HttpTimeout) {
        requestTimeoutMillis = 15000L
        connectTimeoutMillis = 15000L
        socketTimeoutMillis = 15000L
      }
      install(Logging) {
        logger = object : io.ktor.client.plugins.logging.Logger {
          override fun log(message: String) {
            log.d { message }
          }
        }
        level = when (log.config.minSeverity) {
          Severity.Verbose -> {
            LogLevel.ALL
          }

          Severity.Debug -> {
            LogLevel.HEADERS
          }

          else -> {
            LogLevel.INFO
          }
        }
        if (log.config.minSeverity != Severity.Debug) {
          sanitizeHeader { it == HttpHeaders.Authorization }
        }
      }
      defaultRequest {
        url(baseUrl)
      }
      install(responseTimePlugin)
      install(authHeaderPlugin) {
        ignorePathList.add("/pos/v2/login")
      }
    }

    return httpClient
  }

  private val responseTimePlugin = createClientPlugin("ResponseTimePlugin") {
    val onCallTimeKey = AttributeKey<Long>("onCallTimeKey")
    on(SendingRequest) { request, _ ->
      val onCallTime = clock.now().toEpochMilliseconds()
      request.attributes.put(onCallTimeKey, onCallTime)
    }

    onResponse { response ->
      val onCallTime = response.call.attributes[onCallTimeKey]
      val onCallReceiveTime = clock.now().toEpochMilliseconds()
      log.d { "Read response delay (ms): ${onCallReceiveTime - onCallTime}" }
    }
  }

  private val authHeaderPlugin = createClientPlugin("AuthHeaderPlugin",
    HttpClientFactory::AuthHeaderPluginConfig
  ) {
    val ignoreList = pluginConfig.ignorePathList
    on(SendingRequest) { request, _ ->
      if (ignoreList.any { request.url.encodedPath.endsWith(it) }) {
        return@on
      }

      val accessToken = repo.read()?.accessToken
      if (accessToken != null) {
        log.d { "Adding auth header for ${request.url.encodedPath}" }
        request.headers.appendIfNameAbsent(HttpHeaders.Authorization, "Bearer $accessToken")
      }
    }
  }

  class AuthHeaderPluginConfig {
    val ignorePathList = mutableListOf<String>()
  }
}

expect val engine: HttpClientEngineFactory<HttpClientEngineConfig>
expect fun <T : HttpClientEngineConfig> HttpClientConfig<T>.configureEngin()
