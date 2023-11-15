package com.ideabaker.kmp.translator.core.api

enum class ServerError {
  SERVICE_UNAVAILABLE,
  CLIENT_ERROR,
  SERVER_ERROR,
  NOT_FOUND,
  BAD_REQUEST,
  DUPLICATE_ENTITY,
  UNKNOWN_ERROR,
  UNAUTHORIZED
}

class ServerException(val error: ServerError, message: String?): Exception(
  message
)
