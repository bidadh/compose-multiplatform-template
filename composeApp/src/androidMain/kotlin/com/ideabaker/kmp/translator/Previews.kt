package com.ideabaker.kmp.translator

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.touchlab.kermit.Logger
import com.ideabaker.kmp.translator.screen.home.HomeScreen
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginScreen
import com.ideabaker.kmp.translator.screen.login.LoginViewModel
import com.ideabaker.kmp.translator.screen.pin.PinScreen
import com.ideabaker.kmp.translator.service.GreetingService


@Preview(
  name = "Login Screen",
  showSystemUi = true,
  showBackground = false,
)
@Composable
fun LoginScreenPreview() {
  LoginScreen(LoginViewModel(Logger.withTag("LoginViewModel"))) {}
}

@Preview(
  name = "Home Screen",
  showSystemUi = true,
  showBackground = false,
)
@Composable
fun HomeScreenPreview() {
  HomeScreen(viewModel = HomeViewModel(GreetingService())) { }
}

@Preview(
  name = "Pin Screen",
  showSystemUi = true,
  showBackground = false,
)
@Composable
fun PinScreenPreview() {
  PinScreen { }
}
