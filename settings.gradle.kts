rootProject.name = "translator-kmm-compose-app"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":composeApp")

pluginManagement {
  repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    gradlePluginPortal()
    mavenCentral()
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

  versionCatalogs {
    create("moko") {
      from(files("gradle/moko.versions.toml"))
    }
    create("ktor") {
      from(files("gradle/ktor.versions.toml"))
    }
    create("sqlDelight") {
      from(files("gradle/sqldelight.versions.toml"))
    }
    create("koin") {
      from(files("gradle/koin.versions.toml"))
    }
    create("preCompose") {
      from(files("gradle/precompose.versions.toml"))
    }
    create("kermit") {
      from(files("gradle/kermit.versions.toml"))
    }
  }
}
