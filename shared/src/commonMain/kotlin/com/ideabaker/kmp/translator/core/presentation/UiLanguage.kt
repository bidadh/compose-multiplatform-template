package com.ideabaker.kmp.translator.core.presentation

import com.ideabaker.kmp.translator.core.domain.language.Language

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class UiLanguage {
  val language: Language

  companion object {
    fun by(code: String): UiLanguage
    val allLanguages: List<UiLanguage>
  }
}
