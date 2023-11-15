package com.ideabaker.kmp.translator.core.settings

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SettingsFactory(
  private val context: Context
) {
  actual fun create(): Settings {
    val delegate: SharedPreferences = context.getSharedPreferences("default", Context.MODE_PRIVATE)
    return SharedPreferencesSettings(delegate)
  }
}
