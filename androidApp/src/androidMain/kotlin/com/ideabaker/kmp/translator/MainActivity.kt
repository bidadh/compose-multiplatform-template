package com.ideabaker.kmp.translator

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val onStart: () -> Unit = { Log.i("Startup", "Hello from Android/Kotlin!") }
    setContent {
      MainView(initKoin(onStart))
    }
  }
}