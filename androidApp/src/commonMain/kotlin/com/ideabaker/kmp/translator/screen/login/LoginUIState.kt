package com.ideabaker.kmp.translator.screen.login

data class LoginUIState(
  val username: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
)
