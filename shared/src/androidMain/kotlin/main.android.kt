import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

actual fun getPlatformName(): String = Build.MANUFACTURER +
    " " + Build.VERSION.RELEASE

