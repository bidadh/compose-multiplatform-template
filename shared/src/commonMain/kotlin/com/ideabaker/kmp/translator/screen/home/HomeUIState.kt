package com.ideabaker.kmp.translator.screen.home

data class HomeUIState(
  val greeting: String = "Hello translator app!",
  val isLoading: Boolean = false,
  val showImage: Boolean = false,
)