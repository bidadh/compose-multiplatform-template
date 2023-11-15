package com.ideabaker.kmp.translator.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SettingsFactory {
  actual fun create(): Settings {
    val delegate: NSUserDefaults = NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(delegate)
  }
}
