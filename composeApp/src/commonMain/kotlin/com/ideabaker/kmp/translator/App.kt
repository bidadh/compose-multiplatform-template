package com.ideabaker.kmp.translator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ideabaker.kmp.translator.screen.Nav
import com.ideabaker.kmp.translator.core.presentation.theme.AppTheme
import moe.tlaster.precompose.PreComposeApp

@Composable
fun App() {
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

