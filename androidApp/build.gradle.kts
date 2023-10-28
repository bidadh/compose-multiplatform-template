plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.kotlin.kapt)
//  alias(libs.plugins.dagger.hilt)
  alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
  androidTarget()
  sourceSets {
    val androidMain by getting {
      dependencies {
        implementation(project(":shared"))
//        implementation(libs.bundles.compose)
//        implementation(libs.bundles.hilt)
//        implementation(libs.activity.compose)
      }
    }
  }
}

android {
  compileSdk = (findProperty("android.compileSdk") as String).toInt()
  namespace = "com.myapplication"

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

  defaultConfig {
    applicationId = "com.myapplication.MyApplication"
    minSdk = (findProperty("android.minSdk") as String).toInt()
    targetSdk = (findProperty("android.targetSdk") as String).toInt()
    versionCode = 1
    versionName = "1.0"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlin {
    jvmToolchain(17)
  }
}
