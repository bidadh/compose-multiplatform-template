plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.sqldelight)
  alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
  androidTarget()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "shared"
      isStatic = true
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)

        implementation(libs.ktor.core)
        implementation(libs.ktor.serialization)
        implementation(libs.ktor.serialization.json)
        implementation(libs.sql.delight.runtime)
        implementation(libs.sql.delight.coroutines.extensions)
        implementation(libs.kotlin.dateTime)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation(libs.assert.ko)
        implementation(libs.turbine)
      }
    }

    val androidMain by getting {
      dependencies {
        implementation(compose.preview)
        api(libs.activity.compose)
        api(libs.appcompat)
        api(libs.core.ktx)
        implementation(libs.ktor.android)
        implementation(libs.sql.delight.android.driver)
      }
    }
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)

      dependencies {
        implementation(libs.ktor.ios)
        implementation(libs.sql.delight.native.driver)
      }
    }

    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

android {
  compileSdk = (findProperty("android.compileSdk") as String).toInt()
  namespace = "com.myapplication.common"

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = (findProperty("android.minSdk") as String).toInt()
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlin {
    jvmToolchain(17)
  }
}
