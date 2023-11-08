package com.ideabaker.kmp.translator

import android.content.Context
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.dsl.bind
import org.koin.dsl.module

fun MainActivity.initKoin(): KoinApplication.() -> Unit {
  val androidContext = module {
    single<Context> { this@initKoin } bind Context::class
  }
  val androidAppModules = androidContext + appModule()
  val koinAppInitializer: KoinApplication.() -> Unit = {
    androidLogger()
    printLogger()
    modules(androidAppModules)
  }

  return koinAppInitializer
}
