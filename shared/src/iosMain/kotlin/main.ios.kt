import androidx.compose.ui.window.ComposeUIViewController
import com.ideabaker.kmp.translator.App
import org.koin.compose.KoinApplication

@Suppress("FunctionName", "unused")
fun MainViewController() = ComposeUIViewController {
  KoinApplication(application = initKoin()) {
    App()
  }
}