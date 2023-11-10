import com.ideabaker.kmp.translator.customizeKoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(doOnStartup: () -> Unit): KoinAppDeclaration =
  customizeKoinApplication(
    module {
      single { doOnStartup }
    }
  )