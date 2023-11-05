package com.ideabaker.kmp.translator.translate.domain.translate

import com.ideabaker.kmp.translator.core.domain.language.Language

interface TranslateClient {
  suspend fun translate(
    fromLanguage: Language,
    fromText: String,
    toLanguage: Language
  ): String
}