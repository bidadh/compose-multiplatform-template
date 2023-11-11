package com.ideabaker.kmp.translator.translate.presentation

import com.ideabaker.kmp.translator.core.domain.util.Resource
import com.ideabaker.kmp.translator.core.domain.util.toCommonStateFlow
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import com.ideabaker.kmp.translator.translate.domain.history.HistoryDataSource
import com.ideabaker.kmp.translator.translate.domain.history.HistoryItem
import com.ideabaker.kmp.translator.translate.domain.translate.Translate
import com.ideabaker.kmp.translator.translate.domain.translate.TranslateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel

class TranslateViewModel(
  private val translate: Translate,
  historyDataSource: HistoryDataSource,
  coroutineScope: CoroutineScope?
): ViewModel() {

  private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
  private val _uiState = MutableStateFlow(TranslateState())

  val state = combine(_uiState, historyDataSource.getHistory()) { state, history ->
    combineWithHistory(state, history)
  }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState())
    .toCommonStateFlow()

  private fun combineWithHistory(
    state: TranslateState,
    history: List<HistoryItem>
  ): TranslateState {
    if (state.history == history) {
      return state
    }
    return state.copy(
      history = history.mapNotNull { item ->
        UiHistoryItem(
          id = item.id ?: return@mapNotNull null,
          fromText = item.fromText,
          toText = item.toText,
          fromLanguage = UiLanguage.by(code = item.fromLanguageCode),
          toLanguage = UiLanguage.by(code = item.toLanguageCode)
        )
      }
    )
  }

  private var translateJob: Job? = null

  fun onEvent(event: TranslateEvent) {
    when (event) {
      is TranslateEvent.ChangeTranslationText -> {
        _uiState.update {
          it.copy(
            fromText = event.text
          )
        }
      }

      is TranslateEvent.ChooseFromLanguage -> {
        _uiState.update {
          it.copy(
            isChoosingFromLanguage = false,
            fromLanguage = event.language
          )
        }
      }

      is TranslateEvent.ChooseToLanguage -> {
        val newState = _uiState.updateAndGet {
          it.copy(
            isChoosingToLanguage = false,
            toLanguage = event.language
          )
        }
        translate(newState)
      }

      TranslateEvent.CloseTranslation -> {
        _uiState.update {
          it.copy(
            isTranslating = false,
            fromText = "",
            toText = null
          )
        }
      }

      TranslateEvent.EditTranslation -> {
        if (state.value.toText != null) {
          _uiState.update {
            it.copy(
              toText = null,
              isTranslating = false
            )
          }
        }
      }

      TranslateEvent.OnErrorSeen -> {
        _uiState.update { it.copy(error = null) }
      }

      TranslateEvent.OpenFromLanguageDropDown -> {
        _uiState.update {
          it.copy(
            isChoosingFromLanguage = true
          )
        }
      }

      TranslateEvent.OpenToLanguageDropDown -> {
        _uiState.update {
          it.copy(
            isChoosingToLanguage = true
          )
        }
      }

      is TranslateEvent.SelectHistoryItem -> {
        translateJob?.cancel()
        _uiState.update {
          it.copy(
            fromText = event.item.fromText,
            toText = event.item.toText,
            isTranslating = false,
            fromLanguage = event.item.fromLanguage,
            toLanguage = event.item.toLanguage
          )
        }
      }

      TranslateEvent.StopChoosingLanguage -> {
        _uiState.update {
          it.copy(
            isChoosingFromLanguage = false,
            isChoosingToLanguage = false
          )
        }
      }

      is TranslateEvent.SubmitVoiceResult -> {
        _uiState.update {
          it.copy(
            fromText = event.result ?: it.fromText,
            isTranslating = if (event.result != null) false else it.isTranslating,
            toText = if (event.result != null) null else it.toText
          )
        }
      }

      TranslateEvent.SwapLanguages -> {
        _uiState.update {
          it.copy(
            fromLanguage = it.toLanguage,
            toLanguage = it.fromLanguage,
            fromText = it.toText ?: "",
            toText = if (it.toText != null) it.fromText else null
          )
        }
      }

      TranslateEvent.Translate -> translate(state.value)
      else -> Unit
    }
  }

  private fun translate(state: TranslateState) {
    if (state.isTranslating || state.fromText.isBlank()) {
      return
    }

    translateJob = viewModelScope.launch {
      _uiState.update {
        it.copy(
          isTranslating = true
        )
      }
      val result = translate.execute(
        fromLanguage = state.fromLanguage.language,
        fromText = state.fromText,
        toLanguage = state.toLanguage.language
      )
      when (result) {
        is Resource.Success -> {
          _uiState.update {
            it.copy(
              isTranslating = false,
              toText = result.data
            )
          }
        }

        is Resource.Error -> {
          _uiState.update {
            it.copy(
              isTranslating = false,
              error = (result.throwable as? TranslateException)?.error
            )
          }
        }
      }
    }
  }
}