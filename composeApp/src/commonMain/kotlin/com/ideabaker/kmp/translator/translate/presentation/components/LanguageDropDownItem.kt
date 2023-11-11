package com.ideabaker.kmp.translator.translate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LanguageDropDownItem(
  language: UiLanguage,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  DropdownMenuItem(
    onClick = onClick,
    modifier = modifier
  ) {
    Image(
      painter = painterResource(res = "drawable/${language.language.langName.lowercase()}.xml"),
      contentDescription = language.language.langName,
      modifier = Modifier.size(40.dp)
    )
    Spacer(modifier = Modifier.width(16.dp))
    Text(
      text = language.language.langName
    )
  }
}