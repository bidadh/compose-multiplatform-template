package com.ideabaker.kmp.translator.screen.login

import co.touchlab.kermit.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class LoginViewModel(
  private val log: Logger
) : ViewModel() {
  private var loginJob: Job? = null

  private val _uiState = MutableStateFlow(LoginState())
  val state = _uiState.asStateFlow()

  fun onEvent(event: LoginEvent) {
    when (event) {
      is LoginEvent.Login -> {
        loginJob?.cancel()
        login(_uiState.value, event.onSuccess)
      }

      is LoginEvent.OnErrorSeen -> {
        _uiState.update {
          it.copy(error = null)
        }
      }

      is LoginEvent.PasswordChanged -> {
        _uiState.update {
          it.copy(password = event.password)
        }
      }

      is LoginEvent.UsernameChanged -> {
        _uiState.update {
          it.copy(username = event.username)
        }
      }
    }
  }

  fun login(state: LoginState, onSuccess: () -> Unit) {
    if (state.isLoading) {
      return
    }

    if (state.username.isEmpty()) {
      _uiState.update {
        it.copy(error = LoginError.USERNAME_EMPTY)
      }
    }

    if (state.password.isEmpty()) {
      _uiState.update {
        it.copy(error = LoginError.PASSWORD_EMPTY)
      }
    }

    log.i { "Logging in..." }
    loginJob = viewModelScope.launch {
      _uiState.update {
        it.copy(
          isLoading = true,
          error = null
        )
      }

      delay(1000)
      _uiState.update {
        it.copy(isLoading = false)
      }

      onSuccess()
    }
  }
}