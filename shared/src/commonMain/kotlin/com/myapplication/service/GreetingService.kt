package com.myapplication.service

import getPlatformName
import org.koin.core.annotation.Single

@Single
class GreetingService {
  fun greeting(): String {
    return getPlatformName()
  }
}