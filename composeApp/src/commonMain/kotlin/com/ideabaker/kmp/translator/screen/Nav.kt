package com.ideabaker.kmp.translator.screen

import androidx.compose.runtime.Composable
import com.ideabaker.kmp.translator.screen.home.HomeScreen
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginScreen
import com.ideabaker.kmp.translator.screen.login.LoginViewModel
import com.ideabaker.kmp.translator.screen.pin.PinScreen
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun Nav() {
  val navigator = rememberNavigator()
  NavHost(
    navigator = navigator,
    navTransition = NavTransition(),
    initialRoute = Routes.Login.route,
  ) {
    scene(
      route = Routes.Home.route,
      navTransition = NavTransition(),
    ) {
      val viewModel = koinViewModel(HomeViewModel::class)
      HomeScreen(viewModel)  {
        navigator.navigate(Routes.Login.route)
      }
    }

    scene(
      route = Routes.Login.route,
      navTransition = NavTransition(),
    ) {
      val viewModel = koinViewModel(LoginViewModel::class)
      LoginScreen(viewModel) {
        navigator.navigate(Routes.Pin.route)
      }
    }

    scene(
      route = Routes.Pin.route,
      navTransition = NavTransition(),
    ) {
      PinScreen {
        navigator.navigate(Routes.Login.route)
      }
    }
  }
}