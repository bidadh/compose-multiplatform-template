package com.ideabaker.kmp.translator.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ideabaker.kmp.translator.screen.Routes
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigate: (Routes) -> Unit) {
  val uiState by viewModel.uiState.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.greeting()
  }

  Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Button(onClick = {
      viewModel.update()
    }) {
      Text(viewModel.uiState.value.greeting)
    }
    TextField(value = viewModel.uiState.value.greeting, onValueChange = { viewModel.update(it) })
    AnimatedVisibility(viewModel.uiState.value.showImage) {
      Image(
        painterResource("compose-multiplatform.xml"),
        null
      )
      onNavigate.invoke(Routes.Login)
    }
    this@Column.AnimatedVisibility(
      uiState.isLoading,
      enter = fadeIn(),
      exit = fadeOut()
    ) {
      CircularProgressIndicator()
    }
  }
}
