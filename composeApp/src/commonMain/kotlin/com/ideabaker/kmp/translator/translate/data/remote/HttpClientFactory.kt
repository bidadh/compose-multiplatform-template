package com.ideabaker.kmp.translator.translate.data.remote

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json

expect val engine: HttpClientEngineFactory<HttpClientEngineConfig>

object HttpClientFactory {
  fun create(log: Logger): HttpClient {
    return HttpClient(engine) {
      configureEngin()
      install(ContentNegotiation) {
        json()
      }
      install(Auth)
      install(Logging) {
        logger = object : io.ktor.client.plugins.logging.Logger {
          override fun log(message: String) {
            log.i { message }
          }
        }
        level = LogLevel.ALL
        sanitizeHeader { it == HttpHeaders.Authorization }
      }
    }
  }
}

expect fun <T: HttpClientEngineConfig> HttpClientConfig<T>.configureEngin()
