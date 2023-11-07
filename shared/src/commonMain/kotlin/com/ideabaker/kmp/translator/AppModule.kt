package com.ideabaker.kmp.translator

import com.ideabaker.kmp.translator.home.HomeViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

val viewModelsModule = module {
  factory {
    HomeViewModel(get())
  }
}
fun appModule() = listOf(
  AppModule().module,
  viewModelsModule
)

@Module
@ComponentScan("com.ideabaker.kmp.translator")
class AppModule
