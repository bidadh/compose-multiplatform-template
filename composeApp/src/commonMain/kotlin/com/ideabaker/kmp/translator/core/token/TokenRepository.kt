package com.ideabaker.kmp.translator.core.token

import co.touchlab.kermit.Logger
import com.ideabaker.kmp.translator.core.api.ServerError
import com.ideabaker.kmp.translator.core.api.ServerException
import io.ktor.client.plugins.auth.providers.BearerTokens

class TokenRepository(
  private val log: Logger,
) {
  private val bearerTokenStorage = mutableListOf<BearerTokens>()

  fun add(token: Token) {
    log.i { "Adding token to storage" }
    try {
      bearerTokenStorage.add(BearerTokens(token.accessToken, ""))
    } catch (e: Exception) {
      log.w { "Unable to add token to storage with error: $e" }
      throw ServerException(ServerError.CLIENT_ERROR, e.message)
    }
  }

  fun read(): BearerTokens? {
    log.i { "Reading token from storage" }
    log.i { "Token storage: $bearerTokenStorage" }
    return bearerTokenStorage.firstOrNull()
  }
}
