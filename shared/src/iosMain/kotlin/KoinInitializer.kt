import com.ideabaker.kmp.translator.appModule
import org.koin.core.KoinApplication

fun initKoin(): KoinApplication.() -> Unit = {
  modules(appModule())
  printLogger()
}