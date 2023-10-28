rootProject.name = "translator-kmm-compose-app"

include(":androidApp")
include(":shared")

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }

  plugins {
    val kotlinVersion = extra["kotlin.version"] as String
    val agpVersion = extra["agp.version"] as String
    val composeVersion = extra["compose.version"] as String
    val sqlDelightGradleVersion = extra["sqldelight.version"] as String

    kotlin("jvm").version(kotlinVersion)
    kotlin("multiplatform").version(kotlinVersion)
    kotlin("android").version(kotlinVersion)
    kotlin("plugin.serialization").version(kotlinVersion)
    id("kotlin-kapt").version(kotlinVersion)
//    val hiltVersion = extra["hilt.version"] as String
//    id("dagger.hilt.android.plugin").version(hiltVersion)

    id("com.android.application").version(agpVersion)
    id("com.android.library").version(agpVersion)

    id("com.squareup.sqldelight").version(sqlDelightGradleVersion)

    id("org.jetbrains.compose").version(composeVersion)
  }

  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "dagger.hilt.android.plugin") {
        useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
        useModule("com.google.dagger:hilt-android:${requested.version}")
      }
    }
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version ("0.7.0")
}

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}
