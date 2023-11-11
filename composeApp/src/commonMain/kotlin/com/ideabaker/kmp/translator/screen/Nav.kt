package com.ideabaker.kmp.translator.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ideabaker.kmp.translator.screen.home.HomeScreen
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginScreen
import com.ideabaker.kmp.translator.screen.login.LoginViewModel
import com.ideabaker.kmp.translator.screen.pin.PinScreen
import com.ideabaker.kmp.translator.translate.presentation.TranslateScreen
import com.ideabaker.kmp.translator.translate.presentation.TranslateViewModel
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
      val state by viewModel.state.collectAsState()
      LoginScreen(state, viewModel::onEvent) {
        navigator.navigate(Routes.Translate.route)
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

    scene(
      route = Routes.Translate.route,
      navTransition = NavTransition(),
    ) {
      val viewModel = koinViewModel(TranslateViewModel::class)
      val state by viewModel.state.collectAsState()
      TranslateScreen(state, viewModel::onEvent)
    }
  }
}