package com.ideabaker.kmp.translator.translate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SwapLanguagesButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  IconButton(
    onClick = onClick,
    modifier = modifier
      .clip(CircleShape)
      .background(MaterialTheme.colors.primary)
  ) {
    Icon(
      painter = painterResource("drawable/swap_languages.xml"),
      contentDescription = "Swap languages",
      tint = MaterialTheme.colors.onPrimary
    )
  }
}
