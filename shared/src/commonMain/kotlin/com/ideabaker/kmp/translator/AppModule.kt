package com.ideabaker.kmp.translator

import com.ideabaker.kmp.translator.database.TranslateDatabase
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginViewModel
import com.ideabaker.kmp.translator.translate.data.remote.HttpClientFactory
import com.ideabaker.kmp.translator.translate.presentation.TranslateViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

val viewModelsModule = module {
  factory { HomeViewModel(get()) }
  factory { LoginViewModel() }
  factory { TranslateViewModel(get(), get(), null) }
}

expect val platformModule: org.koin.core.module.Module

val httpModule = module {
  factory { HttpClientFactory().create() }
}

val databaseModule = module {
  factory { TranslateDatabase.invoke(get()) }
}

val annotatedModule = AppModule().module

fun appModule() = listOf(
  httpModule,
  platformModule,
  databaseModule,
  annotatedModule,
  viewModelsModule
)

@Module
@ComponentScan("com.ideabaker.kmp.translator")
class AppModule

