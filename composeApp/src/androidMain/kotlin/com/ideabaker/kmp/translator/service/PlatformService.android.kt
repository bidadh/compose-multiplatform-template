package com.ideabaker.kmp.translator.service

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.annotation.RequiresApi
import java.util.UUID

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformService(
  private val context: Context
) {
  @RequiresApi(Build.VERSION_CODES.O)
  actual fun platform(): String {
    return Build.MANUFACTURER +
        " " + Build.VERSION.RELEASE
  }

  @RequiresApi(Build.VERSION_CODES.O)
  actual fun deviceId(): String {
    try {
      val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
      val tmDevice: String = try {
        "" + tm.imei
      } catch (e: Exception) {
        ""
      }
      val tmSerial: String = try {
        "" + tm.simSerialNumber
      } catch (e: Exception) {
        ""
      }
      val androidId: String = "" + Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
      if (!(TextUtils.isEmpty(tmDevice) && TextUtils.isEmpty(tmSerial) && TextUtils.isEmpty(
          androidId
        ))
      ) {
        val deviceUuid = UUID(
          androidId.hashCode().toLong(),
          tmDevice.hashCode().toLong() shl 32 or tmSerial.hashCode().toLong()
        )
        return deviceUuid.toString()
      }
    } catch (_: Exception) {
    } catch (_: Error) {
    }
    return ""
  }
}
