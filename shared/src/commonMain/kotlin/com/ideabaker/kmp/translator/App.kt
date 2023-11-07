package com.ideabaker.kmp.translator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ideabaker.kmp.translator.screen.Nav
import com.ideabaker.kmp.translator.ui.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinApplication

@Composable
fun App() {
  KoinApplication(
    application = {
      modules(appModule())
      printLogger()
    }
  ) {
    PreComposeApp {
      AppTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          Nav()
        }
      }
    }
  }
}

