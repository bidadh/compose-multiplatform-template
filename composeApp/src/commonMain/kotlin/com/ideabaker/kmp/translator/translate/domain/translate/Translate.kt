package com.ideabaker.kmp.translator.translate.domain.translate

import com.ideabaker.kmp.translator.core.domain.language.Language
import com.ideabaker.kmp.translator.core.domain.util.Resource
import com.ideabaker.kmp.translator.translate.domain.history.HistoryDataSource
import com.ideabaker.kmp.translator.translate.domain.history.HistoryItem
import org.koin.core.annotation.Single

@Single
class Translate(
  private val client: TranslateClient,
  private val historyDataSource: HistoryDataSource
) {

  suspend fun execute(
    fromLanguage: Language,
    fromText: String,
    toLanguage: Language
  ): Resource<String> {
    return try {
      val translatedText = client.translate(
        fromLanguage, fromText, toLanguage
      )

      historyDataSource.saveHistory(
        HistoryItem(
          id = null,
          fromLanguageCode = fromLanguage.langCode,
          fromText = fromText,
          toLanguageCode = toLanguage.langCode,
          toText = translatedText,
        )
      )

      Resource.Success(translatedText)
    } catch(e: TranslateException) {
      e.printStackTrace()
      Resource.Error(e)
    }
  }
}