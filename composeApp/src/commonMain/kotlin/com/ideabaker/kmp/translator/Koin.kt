package com.ideabaker.kmp.translator

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.ideabaker.kmp.translator.config.NetworkConstants
import com.ideabaker.kmp.translator.core.api.HttpClientFactory
import com.ideabaker.kmp.translator.core.api.KtorApiClient
import com.ideabaker.kmp.translator.core.settings.LocalSettingsRepository
import com.ideabaker.kmp.translator.core.token.TokenRepository
import com.ideabaker.kmp.translator.database.TranslateDatabase
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginViewModel
import com.ideabaker.kmp.translator.translate.presentation.TranslateViewModel
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun customizeKoinApplication(customModule: Module): KoinAppDeclaration {
  val koinAppInitializer: KoinAppDeclaration = {
    printLogger()
    modules(appModule(customModule))

    koin.get<() -> Unit>().invoke()
  }

  return koinAppInitializer
}

val commonModule = module {
  single { LocalSettingsRepository(get(), getWith("LocalSettingsRepository")) }
}

val viewModelsModule = module {
  factory { HomeViewModel(get()) }
  factory { LoginViewModel(getWith("LoginViewModel")) }
  factory { TranslateViewModel(get(), get(), null) }
}

expect val platformModule: Module

val httpModule = module {
  single { TokenRepository(getWith("TokenRepository")) }
  single {
    val factory = HttpClientFactory(
      repo = get(),
      clock = get(),
      log = getWith("HttpClient"),
      baseUrl = NetworkConstants.UAT.BASE_URL
    )

    factory.create()
  }
  single { KtorApiClient(get(), getWith("KtorApiClient")) }
}

val databaseModule = module {
  single { TranslateDatabase.invoke(get()) }
}

val annotatedModule = AppModule().module

val coreModule = module {
  single<Clock> {
    Clock.System
  }

  // platformLogWriter() is a relatively simple config option, useful for local debugging. For production
  // uses you *may* want to have a more robust configuration from the native platform. In KaMP Kit,
  // that would likely go into platformModule expect/actual.
  // See https://github.com/touchlab/Kermit
  val config = StaticConfig(
    logWriterList = listOf(platformLogWriter()),
    minSeverity = Severity.Verbose
  )
  val baseLogger =
    Logger(config = config, "Translator")
  factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
  return get(parameters = { parametersOf(*params) })
}

// Simple function to clean up the syntax a bit
@Suppress("unused")
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }


fun appModule(module: Module) = listOf(
  module,
  coreModule,
  httpModule,
  platformModule,
  databaseModule,
  annotatedModule,
  commonModule,
  viewModelsModule
)



