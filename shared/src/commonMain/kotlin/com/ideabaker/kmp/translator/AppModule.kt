package com.ideabaker.kmp.translator

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module


fun appModule() = AppModule().module

@Module
@ComponentScan("com.ideabaker.kmp.translator")
class AppModule
