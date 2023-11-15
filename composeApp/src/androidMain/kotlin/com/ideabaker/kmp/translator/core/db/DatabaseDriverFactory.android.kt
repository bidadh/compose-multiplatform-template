package com.ideabaker.kmp.translator.core.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ideabaker.kmp.translator.database.TranslateDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory(
  private val context: Context
) {
  actual fun create(): SqlDriver {
    return AndroidSqliteDriver(TranslateDatabase.Schema, context, dbName)
  }
}
