package com.ideabaker.kmp.translator.translate.data.remote

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HttpClientFactory actual constructor()  {
  actual fun create(log: Logger): HttpClient {
    return HttpClient(Android) {
      install(ContentNegotiation) {
        json()
      }
      installLogger(log)
    }
  }
}