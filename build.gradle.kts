plugins {
  // this is necessary to avoid the plugins to be loaded multiple times
  // in each subproject's classloader
  alias(libs.plugins.jetbrainsCompose).apply(false)
  alias(libs.plugins.androidApplication).apply(false)
  alias(libs.plugins.androidLibrary).apply(false)
  alias(libs.plugins.kotlinMultiplatform).apply(false)
  alias(libs.plugins.ksp).apply(false)
  alias(libs.plugins.sqldelight).apply(false)
  alias(libs.plugins.kotlinSerialization).apply(false)
  alias(moko.plugins.resources).apply(false)
}
buildscript {
  repositories {
    gradlePluginPortal()
  }

  dependencies {
    classpath(moko.resourcesGradlePlugin)
  }
}