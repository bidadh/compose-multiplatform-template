package com.ideabaker.kmp.translator

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun MainActivity.initKoin(onStart: () -> Unit): KoinAppDeclaration {
  val androidModule = module {
    single<Context> { this@initKoin } bind Context::class
    single<SharedPreferences> {
      get<Context>().getSharedPreferences("TRANSLATOR_SETTINGS", Context.MODE_PRIVATE)
    }
    single {
      onStart
    }
  }

  //TODO: add androidLogger()
  return customizeKoinApplication(androidModule)
}
