package com.ideabaker.kmp.translator.service

import com.ideabaker.kmp.translator.getPlatformName
import org.koin.core.annotation.Single

@Single
class GreetingService {
  fun greeting(): String {
    return getPlatformName()
  }
}