package com.ideabaker.kmp.translator

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.res.stringResource

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val appName = resources.getString(R.string.app_name)
    val onStart: () -> Unit = { Log.i("Startup", "Hello from Android/Kotlin! $appName") }
    setContent {
      MainView(initKoin(onStart))
    }
  }
}