package com.ideabaker.kmp.translator.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class CommonMutableStateFlow<T> actual constructor(
  private val flow: MutableStateFlow<T>
) : MutableStateFlow<T> by flow