package com.ideabaker.kmp.translator.translate.data.translate

import com.ideabaker.kmp.translator.config.NetworkConstants
import com.ideabaker.kmp.translator.core.domain.language.Language
import com.ideabaker.kmp.translator.translate.domain.translate.TranslateClient
import com.ideabaker.kmp.translator.translate.domain.translate.TranslateError
import com.ideabaker.kmp.translator.translate.domain.translate.TranslateException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Single

@Single
class KtorTranslateClient(
  private val httpClient: HttpClient
): TranslateClient {
  override suspend fun translate(
    fromLanguage: Language,
    fromText: String,
    toLanguage: Language
  ): String {
    val result = try {
      httpClient.post {
        url(NetworkConstants.BASE_URL + "/translate")
        contentType(ContentType.Application.Json)
        setBody(
          TranslateRequest(
            textToTranslate = fromText,
            sourceLanguageCode = fromLanguage.langCode,
            targetLanguageCode = toLanguage.langCode
          )
        )
      }
    } catch (e: Exception) {
      throw TranslateException(TranslateError.SERVICE_UNAVAILABLE)
    }

    when(result.status.value) {
      in 200 .. 299 -> Unit
      500 -> throw TranslateException(TranslateError.SERVER_ERROR)
      in 400 .. 499 -> throw TranslateException(TranslateError.CLIENT_ERROR)
      else -> throw TranslateException(TranslateError.UNKNOWN_ERROR)
    }

    return try {
      result.body<TranslateResponse>().translatedText
    } catch (e: Exception) {
      throw TranslateException(TranslateError.CLIENT_ERROR)
    }
  }
}