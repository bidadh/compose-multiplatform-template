package com.ideabaker.kmp.translator.core.settings

import com.russhwolf.settings.Settings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SettingsFactory {
  fun create(): Settings
}
