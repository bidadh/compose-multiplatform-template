package com.ideabaker.kmp.translator

import platform.UIKit.UIDevice

actual fun getPlatformName(): String = UIDevice.currentDevice.localizedModel +
    " " + UIDevice.currentDevice.systemName() +
    " " + UIDevice.currentDevice.systemVersion +
    " " + UIDevice.currentDevice.batteryLevel
