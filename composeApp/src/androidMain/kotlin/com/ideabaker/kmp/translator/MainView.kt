package com.ideabaker.kmp.translator

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

@Composable
fun MainView(koinAppInitializer: KoinApplication.() -> Unit) {
  KoinApplication(application = koinAppInitializer) {
    App()
  }
}
