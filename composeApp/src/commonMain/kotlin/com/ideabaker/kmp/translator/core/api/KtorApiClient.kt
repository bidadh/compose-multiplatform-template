package com.ideabaker.kmp.translator.core.api

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.Serializable

@Suppress("unused")
class KtorApiClient(
  val httpClient: HttpClient,
  val log: Logger
) {
  suspend inline fun <reified T> get(block: HttpRequestBuilder.() -> Unit): T {
    val result = try {
      httpClient.get {
        block()
      }
    } catch (e: Exception) {
      log.w { "Unable to load sites with error: $e" }
      throw ServerException(ServerError.SERVICE_UNAVAILABLE, e.message)
    }

    return try {
      val body = result.body<T>()
      log.i { "Saving to storage........" }
      body
    } catch (e: Exception) {
      log.w { "Unable to store token with error: $e" }
      throw ServerException(ServerError.CLIENT_ERROR, e.message)
    }
  }

  suspend inline fun <reified T> post(block: HttpRequestBuilder.() -> Unit): T {
    val result = try {
      httpClient.post {
        block()
      }
    } catch (e: Exception) {
      log.w { "Unable to login with error: $e" }
      throw ServerException(ServerError.SERVICE_UNAVAILABLE, e.message)
    }

    return try {
      val body = result.body<T>()
      log.i { "Saving to storage........" }
      body
    } catch (e: Exception) {
      log.w { "Unable to store token with error: $e" }
      throw ServerException(ServerError.CLIENT_ERROR, e.message)
    }
  }
}

@Suppress("unused")
@Serializable
data class ErrorResponse(
  val errors: List<ErrorResponseItem>
)

@Serializable
data class ErrorResponseItem(
  val errorCode: String,
  val errorMessage: String
)
