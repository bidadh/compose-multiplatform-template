package com.ideabaker.kmp.translator.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreen() {
  val viewModel = koinViewModel(HomeViewModel::class)

  var greetingText by remember { mutableStateOf("Hello translator app!") }
  var showImage by remember { mutableStateOf(false) }

  val uiState by viewModel.uiState.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.greeting()
  }

  Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Button(onClick = {
      greetingText = "Compose ios on ${uiState.greeting}"
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
