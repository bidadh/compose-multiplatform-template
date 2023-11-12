package com.ideabaker.kmp.translator.translate.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import com.ideabaker.kmp.translator.core.presentation.theme.color.BlueColorTheme.LightBlue

@Composable
fun LanguageDisplay(
  language: UiLanguage,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    SmallLanguageIcon(language = language)
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      text = language.language.langName,
      color = LightBlue
    )
  }
}