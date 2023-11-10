package com.ideabaker.kmp.translator.translate.domain.history

import com.ideabaker.kmp.translator.core.domain.util.CommonFlow

interface HistoryDataSource {
  fun getHistory(): CommonFlow<List<HistoryItem>>
  suspend fun saveHistory(item: HistoryItem)
}