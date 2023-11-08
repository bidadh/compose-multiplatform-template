package com.ideabaker.kmp.translator.translate.presentation

import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import com.ideabaker.kmp.translator.translate.domain.translate.TranslateError

data class TranslateState(
  val fromText: String = "",
  val toText: String? = null,
  val isTranslating: Boolean = false,
  val fromLanguage: UiLanguage = UiLanguage.by(code = "en"),
  val toLanguage: UiLanguage = UiLanguage.by(code = "fa"),
  val isChoosingFromLanguage: Boolean = false,
  val isChoosingToLanguage: Boolean = false,
  val error: TranslateError? = null,
  val history: List<UiHistoryItem> = emptyList()
)