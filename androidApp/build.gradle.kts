plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.dagger.hilt)
  alias(libs.plugins.kotlin.plugin.serialization)
  alias(libs.plugins.ksp)
}

kotlin {
  androidTarget()
  sourceSets {
    val androidMain by getting {
      dependencies {
      }
    }
  }
}

android {
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  namespace = "com.ideabaker.kmp.translator"

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

  defaultConfig {
    applicationId = "com.ideabaker.kmp.translator.TranslatorApp"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  applicationVariants.all {
    val variantName = name
    sourceSets {
      getByName("main") {
        java.srcDir(File("build/generated/ksp/$variantName/kotlin"))
      }
    }
  }
  kotlin {
    jvmToolchain(17)
  }
}

dependencies {
  implementation(project(":shared"))
  implementation(compose.ui)
  implementation(compose.uiTooling)
  implementation(compose.preview)
  implementation(compose.foundation)
  implementation(compose.material3)
  implementation(compose.materialIconsExtended)
  implementation(libs.coil.compose)
  implementation(libs.compose.navigation)
  implementation(libs.activity.compose)
  implementation(libs.activity)
  implementation(libs.ktor.android)
  implementation(libs.hilt.android)
  myKapt(libs.hilt.android.compiler)
  implementation(libs.hilt.work)
  myKapt(libs.hilt.compiler)
  implementation(libs.work.runtime.ktx)
  implementation(libs.hilt.navigation.compose)

  implementation(libs.koin.core)
  implementation(libs.koin.android)
  implementation(libs.koin.compose)
  implementation(libs.koin.annotations)
  ksp(libs.koin.ksp.compiler)
}
