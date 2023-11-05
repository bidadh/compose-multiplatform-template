package com.ideabaker.kmp.translator.service

import org.koin.core.annotation.Single

@Single
class GreetingService {
  private val platformService: PlatformService = PlatformService()

  fun greeting(): String {
    return platformService.platform()
  }
}