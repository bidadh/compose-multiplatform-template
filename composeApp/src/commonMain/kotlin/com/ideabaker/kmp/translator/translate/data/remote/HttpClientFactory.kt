package com.ideabaker.kmp.translator.translate.data.remote

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HttpClientFactory() {
  fun create(log: Logger): HttpClient
}

fun <T: HttpClientEngineConfig> HttpClientConfig<T>.installLogger(log: Logger) {
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
