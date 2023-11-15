package com.ideabaker.kmp.translator.core.api

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig

actual val engine: HttpClientEngineFactory<HttpClientEngineConfig>
  get() = Darwin

actual fun <T : HttpClientEngineConfig> HttpClientConfig<T>.configureEngin() {
  engine {
    val config = this as DarwinClientEngineConfig
    config.configureRequest {
      setAllowsCellularAccess(true)
    }
  }
}
