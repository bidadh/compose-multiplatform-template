package com.ideabaker.kmp.translator.translate.data.translate

import kotlinx.serialization.Serializable

@Serializable
data class TranslateResponse(
  val translatedText: String
)