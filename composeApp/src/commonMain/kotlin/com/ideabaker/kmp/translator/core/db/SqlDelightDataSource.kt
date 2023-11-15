package com.ideabaker.kmp.translator.core.db

import org.koin.core.annotation.Single
import com.ideabaker.kmp.translator.database.TranslateDatabase

@Single
class SqlDelightDataSource(
  db: TranslateDatabase
)
