package com.ideabaker.kmp.translator.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.ideabaker.kmp.translator.core.presentation.theme.color.BlueColorTheme
import com.ideabaker.kmp.translator.core.presentation.theme.color.ColorTheme


val colorTheme: ColorTheme = BlueColorTheme
private val LightColors = lightColorScheme(
    primary = colorTheme.md_theme_light_primary,
    onPrimary = colorTheme.md_theme_light_onPrimary,
    primaryContainer = colorTheme.md_theme_light_primaryContainer,
    onPrimaryContainer = colorTheme.md_theme_light_onPrimaryContainer,
    secondary = colorTheme.md_theme_light_secondary,
    onSecondary = colorTheme.md_theme_light_onSecondary,
    secondaryContainer = colorTheme.md_theme_light_secondaryContainer,
    onSecondaryContainer = colorTheme.md_theme_light_onSecondaryContainer,
    tertiary = colorTheme.md_theme_light_tertiary,
    onTertiary = colorTheme.md_theme_light_onTertiary,
    tertiaryContainer = colorTheme.md_theme_light_tertiaryContainer,
    onTertiaryContainer = colorTheme.md_theme_light_onTertiaryContainer,
    error = colorTheme.md_theme_light_error,
    errorContainer = colorTheme.md_theme_light_errorContainer,
    onError = colorTheme.md_theme_light_onError,
    onErrorContainer = colorTheme.md_theme_light_onErrorContainer,
    background = colorTheme.md_theme_light_background,
    onBackground = colorTheme.md_theme_light_onBackground,
    surface = colorTheme.md_theme_light_surface,
    onSurface = colorTheme.md_theme_light_onSurface,
    surfaceVariant = colorTheme.md_theme_light_surfaceVariant,
    onSurfaceVariant = colorTheme.md_theme_light_onSurfaceVariant,
    outline = colorTheme.md_theme_light_outline,
    inverseOnSurface = colorTheme.md_theme_light_inverseOnSurface,
    inverseSurface = colorTheme.md_theme_light_inverseSurface,
    inversePrimary = colorTheme.md_theme_light_inversePrimary,
    surfaceTint = colorTheme.md_theme_light_surfaceTint,
    outlineVariant = colorTheme.md_theme_light_outlineVariant,
    scrim = colorTheme.md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
  primary = colorTheme.md_theme_dark_primary,
  onPrimary = colorTheme.md_theme_dark_onPrimary,
  primaryContainer = colorTheme.md_theme_dark_primaryContainer,
  onPrimaryContainer = colorTheme.md_theme_dark_onPrimaryContainer,
  secondary = colorTheme.md_theme_dark_secondary,
  onSecondary = colorTheme.md_theme_dark_onSecondary,
  secondaryContainer = colorTheme.md_theme_dark_secondaryContainer,
  onSecondaryContainer = colorTheme.md_theme_dark_onSecondaryContainer,
  tertiary = colorTheme.md_theme_dark_tertiary,
  onTertiary = colorTheme.md_theme_dark_onTertiary,
  tertiaryContainer = colorTheme.md_theme_dark_tertiaryContainer,
  onTertiaryContainer = colorTheme.md_theme_dark_onTertiaryContainer,
  error = colorTheme.md_theme_dark_error,
  errorContainer = colorTheme.md_theme_dark_errorContainer,
  onError = colorTheme.md_theme_dark_onError,
  onErrorContainer = colorTheme.md_theme_dark_onErrorContainer,
  background = colorTheme.md_theme_dark_background,
  onBackground = colorTheme.md_theme_dark_onBackground,
  surface = colorTheme.md_theme_dark_surface,
  onSurface = colorTheme.md_theme_dark_onSurface,
  surfaceVariant = colorTheme.md_theme_dark_surfaceVariant,
  onSurfaceVariant = colorTheme.md_theme_dark_onSurfaceVariant,
  outline = colorTheme.md_theme_dark_outline,
  inverseOnSurface = colorTheme.md_theme_dark_inverseOnSurface,
  inverseSurface = colorTheme.md_theme_dark_inverseSurface,
  inversePrimary = colorTheme.md_theme_dark_inversePrimary,
  surfaceTint = colorTheme.md_theme_dark_surfaceTint,
  outlineVariant = colorTheme.md_theme_dark_outlineVariant,
  scrim = colorTheme.md_theme_dark_scrim,
)

@Composable
fun AppTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (!useDarkTheme) {
    LightColors
  } else {
    DarkColors
  }

  MaterialTheme(
    colorScheme = colors,
    content = content
  )
}