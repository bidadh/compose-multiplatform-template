package com.ideabaker.kmp.translator.screen.home

import com.ideabaker.kmp.translator.service.GreetingService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel(
  private val greetingService: GreetingService
): ViewModel() {
  private val _uiState = MutableStateFlow(HomeUIState())
  val uiState = _uiState.asStateFlow()

  fun greeting() = viewModelScope.launch {
    _uiState.update {
      it.copy(greeting = greetingService.greeting())
    }
  }
}