package com.ideabaker.kmp.translator.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ideabaker.kmp.translator.screen.Routes

@Composable
fun LoginScreen(
  viewModel: LoginViewModel,
  onNavigate: (Routes) -> Unit
) {
  Box(modifier = Modifier.fillMaxSize()) {
    ClickableText(
      text = AnnotatedString("Sign up here"),
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(20.dp),
      onClick = { },
      style = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.secondary
      )
    )
  }
  Column(
    modifier = Modifier.padding(20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    val uiState by viewModel.uiState.collectAsState()

    Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
      label = { Text(text = "Username") },
      value = uiState.username,
      onValueChange = { viewModel.username(it) })

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
      label = { Text(text = "Password") },
      value = uiState.password,
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      onValueChange = { viewModel.password(it) })

    Spacer(modifier = Modifier.height(20.dp))
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
      Button(
        onClick = {
          viewModel.login {
            onNavigate(Routes.Home)
          }
        },
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
      ) {
        Text(text = "Login")
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
    ClickableText(
      text = AnnotatedString("Sign up here"),
      onClick = { onNavigate(Routes.Home) },
      style = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.primary
      )
    )

    this@Column.AnimatedVisibility(
      uiState.isLoading,
      enter = fadeIn(),
      exit = fadeOut()
    ) {
      CircularProgressIndicator()
    }
  }
}
