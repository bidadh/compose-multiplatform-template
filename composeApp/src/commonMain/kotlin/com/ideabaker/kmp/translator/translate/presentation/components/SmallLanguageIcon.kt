package com.ideabaker.kmp.translator.translate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SmallLanguageIcon(
  language: UiLanguage,
  modifier: Modifier = Modifier
) {
  Image(
    painter = painterResource(res = "drawable/${language.language.langName.lowercase()}.xml"),
    contentDescription = language.language.langName,
    modifier = modifier.size(25.dp)
  )
}