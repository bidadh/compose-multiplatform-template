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

    kotlin("jvm").version(kotlinVersion)
    kotlin("multiplatform").version(kotlinVersion)
    kotlin("android").version(kotlinVersion)

    id("com.android.application").version(agpVersion)
    id("com.android.library").version(agpVersion)

    id("org.jetbrains.compose").version(composeVersion)
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