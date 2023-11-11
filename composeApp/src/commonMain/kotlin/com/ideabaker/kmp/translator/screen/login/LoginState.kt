package com.ideabaker.kmp.translator.screen.login

data class LoginState(
  val username: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
  val error: LoginError? = null
)
