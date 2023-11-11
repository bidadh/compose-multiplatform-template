package com.ideabaker.kmp.translator.translate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LanguageDropDown(
  language: UiLanguage,
  isOpen: Boolean,
  onClick: () -> Unit,
  onDismiss: () -> Unit,
  onSelectLanguage: (UiLanguage) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier) {
    DropdownMenu(
      expanded = isOpen,
      onDismissRequest = onDismiss
    ) {
      UiLanguage.allLanguages.forEach { language ->
        LanguageDropDownItem(
          language = language,
          onClick = {
            onSelectLanguage(language)
          },
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
    Row(
      modifier = Modifier
        .clickable(onClick = onClick)
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(res = "drawable/${language.language.langName.lowercase()}.xml"),
        contentDescription = language.language.langName,
        modifier = Modifier.size(30.dp)
      )
      Spacer(modifier = Modifier.width(16.dp))
      Text(
        text = language.language.langName,
        color = MaterialTheme.colorScheme.primary
      )
      Icon(
        imageVector = if(isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
        contentDescription = if(isOpen) {
          "Close"
        } else {
          "Open"
        },
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(30.dp)
      )
    }
  }
}