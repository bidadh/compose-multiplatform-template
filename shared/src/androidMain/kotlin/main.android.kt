import android.os.Build
import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = Build.MANUFACTURER +
    " " + Build.VERSION.RELEASE

@Composable
fun MainView() = App()
