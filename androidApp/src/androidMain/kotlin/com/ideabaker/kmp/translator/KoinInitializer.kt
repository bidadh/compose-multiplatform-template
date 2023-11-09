package com.ideabaker.kmp.translator

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.dsl.bind
import org.koin.dsl.module

fun MainActivity.initKoin(
  onStart: () -> Unit
): KoinApplication.() -> Unit {
  val androidModule = module {
    single<Context> { this@initKoin } bind Context::class
    single<SharedPreferences> {
      get<Context>().getSharedPreferences("TRANSLATOR_SETTINGS", Context.MODE_PRIVATE)
    }
    single {
      onStart
    }
  }
  val koinAppInitializer: KoinApplication.() -> Unit = {
    androidLogger()
    printLogger()
    modules(appModule(androidModule))

    val doOnStartup = koin.get<() -> Unit>()
    doOnStartup.invoke()
  }

  return koinAppInitializer
}
