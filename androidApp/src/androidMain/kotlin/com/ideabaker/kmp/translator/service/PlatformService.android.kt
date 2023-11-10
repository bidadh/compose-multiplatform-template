package com.ideabaker.kmp.translator.service

import android.os.Build

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformService {
  actual fun platform(): String {
    return Build.MANUFACTURER +
        " " + Build.VERSION.RELEASE
  }
}