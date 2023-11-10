package com.ideabaker.kmp.translator

import app.cash.sqldelight.db.SqlDriver
import com.ideabaker.kmp.translator.translate.data.local.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
  single<SqlDriver> { DatabaseDriverFactory(get()).create() }
}
