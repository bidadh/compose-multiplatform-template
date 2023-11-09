import androidx.compose.ui.window.ComposeUIViewController
import co.touchlab.kermit.Logger
import com.ideabaker.kmp.translator.App
import org.koin.compose.KoinApplication
import platform.Foundation.NSUserDefaults

@Suppress("FunctionName", "unused")
fun MainViewController() = ComposeUIViewController {
  val onStartup: () -> Unit = {
    Logger.i("Startup") {
      "Hello from iosApp"
    }
  }
  val application = initKoin(
    onStartup
  )
  KoinApplication(application = application) {
    App()
  }
}