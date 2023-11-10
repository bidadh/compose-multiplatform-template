package com.ideabaker.kmp.translator.core.domain.util

import kotlinx.coroutines.flow.Flow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class CommonFlow<T> actual constructor(
  private val flow: Flow<T>
) : Flow<T> by flow
