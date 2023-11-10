package com.ideabaker.kmp.translator.translate.data.history

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ideabaker.kmp.translator.core.domain.util.CommonFlow
import com.ideabaker.kmp.translator.core.domain.util.toCommonFlow
import com.ideabaker.kmp.translator.database.TranslateDatabase
import com.ideabaker.kmp.translator.translate.domain.history.HistoryDataSource
import com.ideabaker.kmp.translator.translate.domain.history.HistoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import org.koin.core.annotation.Single

@Single
class SqlDelightHistoryDataSource(
  db: TranslateDatabase
): HistoryDataSource {

  private val queries = db.translateQueries

  override fun getHistory(): CommonFlow<List<HistoryItem>> {
    return queries
      .getHistory()
      .asFlow()
      .mapToList(Dispatchers.IO)
      .map { history ->
        history.map { it.toHistoryItem() }
      }
      .toCommonFlow()
  }

  override suspend fun saveHistory(item: HistoryItem) {
    queries.saveHistory(
      id = item.id,
      fromLanguageCode = item.fromLanguageCode,
      fromText = item.fromText,
      toLanguageCode = item.toLanguageCode,
      toText = item.toText,
      timestamp = Clock.System.now().toEpochMilliseconds()
    )
  }
}
