package com.ideabaker.kmp.translator.service

import platform.UIKit.UIDevice

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformService {
  actual fun platform(): String {
    return UIDevice.currentDevice.localizedModel +
        " " + UIDevice.currentDevice.systemName() +
        " " + UIDevice.currentDevice.systemVersion +
        " " + UIDevice.currentDevice.batteryLevel
  }
}