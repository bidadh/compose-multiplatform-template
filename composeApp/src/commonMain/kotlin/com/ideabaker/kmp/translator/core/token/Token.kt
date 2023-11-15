package com.ideabaker.kmp.translator.core.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Token(
  @SerialName("access_token")
  val accessToken: String
)
