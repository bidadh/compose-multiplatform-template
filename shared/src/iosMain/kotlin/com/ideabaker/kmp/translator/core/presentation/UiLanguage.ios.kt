package com.ideabaker.kmp.translator.core.presentation

import com.ideabaker.kmp.translator.core.domain.language.Language

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class UiLanguage(
  actual val language: Language,
  val imageName: String
) {
  actual companion object {
    actual fun by(code: String): UiLanguage {
      return allLanguages.find { it.language.langCode == code }
        ?: throw IllegalArgumentException("Invalid or unsupported language code")
    }

    actual val allLanguages: List<UiLanguage>
      get() = Language.entries.map { language ->
        UiLanguage(
          language = language,
          imageName = language.langName.lowercase()
        )
      }
  }
}