package com.ideabaker.kmp.translator

import app.cash.sqldelight.db.SqlDriver
import com.ideabaker.kmp.translator.core.db.DatabaseDriverFactory
import com.ideabaker.kmp.translator.core.settings.SettingsFactory
import com.ideabaker.kmp.translator.service.PlatformService
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
  single<SqlDriver> { DatabaseDriverFactory().create() }
  single { PlatformService() }
  single<Settings> { SettingsFactory().create() }
}
