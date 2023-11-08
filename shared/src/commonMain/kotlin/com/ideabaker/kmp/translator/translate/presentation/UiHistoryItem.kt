package com.ideabaker.kmp.translator.translate.presentation

import com.ideabaker.kmp.translator.core.presentation.UiLanguage

data class UiHistoryItem(
  val id: Long,
  val fromText: String,
  val toText: String,
  val fromLanguage: UiLanguage,
  val toLanguage: UiLanguage
)