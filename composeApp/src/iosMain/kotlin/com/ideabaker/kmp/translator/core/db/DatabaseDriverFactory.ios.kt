package com.ideabaker.kmp.translator.core.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.ideabaker.kmp.translator.database.TranslateDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
  actual fun create(): SqlDriver {
    return NativeSqliteDriver(TranslateDatabase.Schema, dbName)
  }
}
