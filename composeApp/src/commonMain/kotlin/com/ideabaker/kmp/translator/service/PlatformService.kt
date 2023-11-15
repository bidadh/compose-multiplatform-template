package com.ideabaker.kmp.translator.service


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class PlatformService {
  fun platform(): String
  fun deviceId(): String
}
