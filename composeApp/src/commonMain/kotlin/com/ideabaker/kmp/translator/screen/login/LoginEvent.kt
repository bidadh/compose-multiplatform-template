package com.ideabaker.kmp.translator.screen.login

sealed class LoginEvent {
  data class Login(val onSuccess: () -> Unit): LoginEvent()
  data object OnErrorSeen: LoginEvent()
  data class UsernameChanged(val username: String): LoginEvent()
  data class PasswordChanged(val password: String): LoginEvent()
}