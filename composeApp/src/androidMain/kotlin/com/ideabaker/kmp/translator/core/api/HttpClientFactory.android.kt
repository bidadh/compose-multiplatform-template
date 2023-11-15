package com.ideabaker.kmp.translator.core.api

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.android.AndroidEngineConfig

actual val engine: HttpClientEngineFactory<HttpClientEngineConfig>
  get() = Android

actual fun <T : HttpClientEngineConfig> HttpClientConfig<T>.configureEngin() {
  engine {
    val config = this as AndroidEngineConfig
    config.connectTimeout = 100_000
    config.threadsCount = 4
  }
}
