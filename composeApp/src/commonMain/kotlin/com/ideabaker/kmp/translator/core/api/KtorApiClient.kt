package com.ideabaker.kmp.translator.core.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Single

@Single
class KtorApiClient(
  private val httpClient: HttpClient
) {
  suspend fun get(url: String): String {
    val response = httpClient.get {
      url(url)
    }
    return response.bodyAsText()
  }
}

@Serializable
data class ErrorResponse(
  val errors: List<ErrorResponseItem>
)

@Serializable
data class ErrorResponseItem(
  val errorCode: String,
  val errorMessage: String
)
