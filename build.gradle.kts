plugins {
  // this is necessary to avoid the plugins to be loaded multiple times
  // in each subproject's classloader
  alias(libs.plugins.kotlin.multiplatform).apply(false)
  alias(libs.plugins.android.application).apply(false)
  alias(libs.plugins.android.library).apply(false)
  alias(libs.plugins.jetbrains.compose).apply(false)
  alias(libs.plugins.ksp).apply(false)
  alias(libs.plugins.kotlin.kapt).apply(false)
  alias(libs.plugins.sqldelight).apply(false)
  alias(libs.plugins.dagger.hilt).apply(false)
  alias(libs.plugins.kotlin.plugin.serialization).apply(false)
}
