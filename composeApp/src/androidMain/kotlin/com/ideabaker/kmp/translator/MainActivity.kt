package com.ideabaker.kmp.translator

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ideabaker.kmm.translator.shared.MR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    val appName = StringDesc.Resource(MR.strings.appName).toString(this)
    val onStart: () -> Unit = { Log.i("Startup", "Hello from Android/Kotlin! $appName") }
    setContent {
      MainView(initKoin(onStart))
    }
  }
}