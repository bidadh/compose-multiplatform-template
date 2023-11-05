package com.ideabaker.kmp.translator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ideabaker.kmp.translator.service.GreetingService
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import com.ideabaker.kmp.translator.ui.theme.AppTheme

@Composable
fun App() {
  KoinApplication(
    application = {
      modules(appModule())
    }
  ) {
    AppTheme {
      Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
      ) {
        RootScreen()
      }
    }
  }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RootScreen() {
  val service = koinInject<GreetingService>()

  var greetingText by remember { mutableStateOf("Hello translator app!") }
  var showImage by remember { mutableStateOf(false) }
  Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Button(onClick = {
      greetingText = "Compose ios on ${service.greeting()}"
      showImage = !showImage
    }) {
      Text(greetingText)
    }
    TextField(greetingText, onValueChange = { greetingText = it })
    AnimatedVisibility(showImage) {
      Image(
        painterResource("compose-multiplatform.xml"),
        null
      )
    }
  }
}

expect fun getPlatformName(): String