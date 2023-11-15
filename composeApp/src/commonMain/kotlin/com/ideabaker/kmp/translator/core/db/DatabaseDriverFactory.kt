package com.ideabaker.kmp.translator.core.db

import app.cash.sqldelight.db.SqlDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseDriverFactory {
  fun create(): SqlDriver
}

internal const val dbName: String = "translate.db"
