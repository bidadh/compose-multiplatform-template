package com.ideabaker.kmp.translator.screen.login

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class LoginViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(LoginUIState())
  val uiState = _uiState.asStateFlow()

  fun login(onSuccess: () -> Unit) = viewModelScope.launch {

    _uiState.update {
      it.copy(isLoading = true)
    }

    delay(2000)
    onSuccess()
  }

  fun username(value: String) = viewModelScope.launch {
    _uiState.update {
      it.copy(username = value)
    }
  }

  fun password(value: String) = viewModelScope.launch {
    _uiState.update {
      it.copy(password = value)
    }
  }
}