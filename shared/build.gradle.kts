import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.sqldelight)
  alias(libs.plugins.kotlin.plugin.serialization)
  alias(libs.plugins.ksp)
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
//      linkerOpts.add("-lsqlite3") this didn't work!
    }
  }

  sourceSets {
    val commonMain by getting {
      kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)

        implementation(libs.bundles.ktor)
        implementation(libs.bundles.sqlDelight)
        implementation(libs.kotlin.dateTime)

        api(libs.bundles.koinApi)
        api(libs.bundles.precompose)
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
        implementation(compose.ui)
        implementation(compose.uiTooling)
        implementation(compose.preview)
        api(libs.activity.compose)
        api(libs.appcompat)
        api(libs.core.ktx)
        implementation(libs.ktor.android)
        implementation(libs.sqlDelight.android.driver)
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
        implementation(libs.sqlDelight.native.driver)
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
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  namespace = "com.ideabaker.kmp.translator.common"

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlin {
    jvmToolchain(17)
  }
}

dependencies {
  add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}
tasks.withType<KotlinCompile<*>>().configureEach {
  if (name != "kspCommonMainKotlinMetadata") {
    dependsOn("kspCommonMainKotlinMetadata")
  }
}
afterEvaluate {
  val taskList = tasks.filter {
    it.name.contains("SourcesJar", true)
  }
  taskList.forEach {
    println("SourceJarTask====>${it.name}")
    it.dependsOn("kspCommonMainKotlinMetadata")
  }
}

sqldelight {
  databases {
    create("TranslateDatabase") {
      packageName.set("com.ideabaker.kmp.translator.database")
      srcDirs.setFrom("src/commonMain/sqldelight")
    }
  }
}
