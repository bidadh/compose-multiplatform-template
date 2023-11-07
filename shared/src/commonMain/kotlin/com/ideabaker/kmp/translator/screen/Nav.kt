package com.ideabaker.kmp.translator.screen

import androidx.compose.runtime.Composable
import com.ideabaker.kmp.translator.screen.home.HomeScreen
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
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
    initialRoute = Routes.Home.route,
  ) {
    scene(
      route = Routes.Home.route,
      navTransition = NavTransition(),
    ) {
      val viewModel = koinViewModel(HomeViewModel::class)
      HomeScreen(viewModel)
    }
  }
}