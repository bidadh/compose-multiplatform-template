package com.ideabaker.kmp.translator

import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

val viewModelsModule = module {
  factory { HomeViewModel(get()) }
  factory { LoginViewModel() }
}
fun appModule() = listOf(
  AppModule().module,
  viewModelsModule
)

@Module
@ComponentScan("com.ideabaker.kmp.translator")
class AppModule
