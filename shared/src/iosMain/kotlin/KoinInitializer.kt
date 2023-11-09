import com.ideabaker.kmp.translator.appModule
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun initKoin(
  doOnStartup: () -> Unit
): KoinApplication.() -> Unit = {
  val iosModule = module {
    single { doOnStartup }
  }
  modules(appModule(iosModule))
  printLogger()

  koin.get<() -> Unit>().invoke()
}