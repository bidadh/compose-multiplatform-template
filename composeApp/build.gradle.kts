import dev.icerock.gradle.MRVisibility
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(androidX.plugins.application)
  alias(libs.plugins.jetbrainsCompose)
  alias(sqlDelight.plugins.sqldelight)
  alias(libs.plugins.kotlinSerialization)
  alias(koin.plugins.ksp)
  alias(moko.plugins.resources)
  appPropertiesPlugin()
}

kotlin {
  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = "17"
      }
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
      export(moko.resources)
      export(moko.graphics)
    }
  }

  sourceSets {
    androidMainSourceSets()
    commonMainSourceSets()
    iosMainSourceSets()
  }
}

multiplatformResources {
  multiplatformResourcesPackage = appProperties.resourcesSharedPackage // required
  multiplatformResourcesVisibility = MRVisibility.Internal // optional, default Public
  disableStaticFrameworkWarning = true // optional, default false
}

android {
  compileSdk = androidX.versions.compileSdk.get().toInt()
  namespace = appProperties.`package`

  sourceSets {
    named("main") {
      manifest.srcFile("src/androidMain/AndroidManifest.xml")
      res.srcDirs("src/androidMain/res", "src/commonMain/resources")
      resources.srcDirs("src/commonMain/resources")
    }
  }

  defaultConfig {
    applicationId = appProperties.applicationId
    minSdk = androidX.versions.minSdk.get().toInt()
    targetSdk = androidX.versions.targetSdk.get().toInt()
    versionCode = appProperties.version.version
    versionName = appProperties.version.name
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlin {
    jvmToolchain(17)
  }
  dependencies {
    kspCommonMainMetadata(koin.ksp.compiler)
    debugImplementation(compose.uiTooling)

    implementation(libs.bundles.composeX)

    implementation(ktor.android)
    implementation(ktx.workRuntime)

    implementation(koin.bundles.all)
  }
}

tasks.withType<KotlinCompile<*>>().configureEach {
  if (name != "kspCommonMainKotlinMetadata") {
    dependsOn("kspCommonMainKotlinMetadata")
  }
}

sqldelight {
  databases {
    create(appProperties.dbName) {
      packageName.set(appProperties.dbPackage)
      srcDirs.setFrom("src/commonMain/sqldelight")
    }
  }
}

fun KotlinMultiplatformExtension.iosMainSourceSets() {
  sourceSets {
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain.get())
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
      dependencies {
        implementation(ktor.ios)
        implementation(sqlDelight.native.driver)
      }
    }
  }
}

fun KotlinMultiplatformExtension.commonMainSourceSets() {
  sourceSets {
    commonMain {
      kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        @OptIn(ExperimentalComposeLibrary::class)
        implementation(compose.components.resources)

        implementation(compose.material3)
        implementation(compose.materialIconsExtended)

        implementation(ktor.bundles.all)
        implementation(kotlinx.coroutines.core)
        implementation(sqlDelight.bundles.all)
        implementation(libs.kotlin.dateTime)
        api(moko.bundles.resources)

        implementation(kermit.logging)

        api(koin.bundles.allApi)
        api(preCompose.bundles.all)
      }
    }
  }
}

fun KotlinMultiplatformExtension.androidMainSourceSets() {
  sourceSets {
    val androidMain by getting {
      dependsOn(commonMain.get())
      dependencies {
        implementation(compose.ui)
        implementation(compose.preview)

        implementation(libs.bundles.composeX)

        implementation(ktor.android)
        implementation(kotlinx.coroutines.android)
        implementation(ktx.workRuntime)

        implementation(koin.bundles.all)

        api(libs.appcompat)
        api(ktx.core)
        implementation(ktor.android)
        implementation(sqlDelight.android.driver)
      }
    }
  }
}

