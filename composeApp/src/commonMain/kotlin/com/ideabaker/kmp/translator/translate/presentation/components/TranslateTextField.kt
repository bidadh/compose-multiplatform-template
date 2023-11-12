package com.ideabaker.kmp.translator.translate.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material.icons.rounded.Speaker
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ideabaker.kmm.translator.shared.MR
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import com.ideabaker.kmp.translator.core.presentation.theme.color.BlueColorTheme.LightBlue
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TranslateTextField(
  fromText: String,
  toText: String?,
  isTranslating: Boolean,
  fromLanguage: UiLanguage,
  toLanguage: UiLanguage,
  onTranslateClick: () -> Unit,
  onTextChange: (String) -> Unit,
  onCopyClick: (String) -> Unit,
  onCloseClick: () -> Unit,
  onSpeakerClick: () -> Unit,
  onTextFieldClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .shadow(
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
      )
      .clip(RoundedCornerShape(20.dp))
      .gradientSurface()
      .clickable(onClick = onTextFieldClick)
      .padding(16.dp)
  ) {
    AnimatedContent(
      targetState = toText
    ) { toText ->
      if(toText == null || isTranslating) {
        IdleTranslateTextField(
          fromText = fromText,
          isTranslating = isTranslating,
          onTextChange = onTextChange,
          onTranslateClick = onTranslateClick,
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
        )
      } else {
        TranslatedTextField(
          fromText = fromText,
          toText = toText,
          fromLanguage = fromLanguage,
          toLanguage = toLanguage,
          onCopyClick = onCopyClick,
          onCloseClick = onCloseClick,
          onSpeakerClick = onSpeakerClick,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  }
}

@Composable
private fun TranslatedTextField(
  fromText: String,
  toText: String,
  fromLanguage: UiLanguage,
  toLanguage: UiLanguage,
  onCopyClick: (String) -> Unit,
  onCloseClick: () -> Unit,
  onSpeakerClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
  ) {
    LanguageDisplay(language = fromLanguage)
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = fromText,
      color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
      modifier = Modifier.align(Alignment.End)
    ) {
      IconButton(onClick = {
        onCopyClick(fromText)
      }) {
        Icon(
          imageVector = Icons.Rounded.CopyAll,
          contentDescription = stringResource(MR.strings.copy),
          tint = LightBlue
        )
      }
      IconButton(onClick = onCloseClick) {
        Icon(
          imageVector = Icons.Rounded.Close,
          contentDescription = stringResource(MR.strings.close),
          tint = LightBlue
        )
      }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
    Spacer(modifier = Modifier.height(16.dp))
    LanguageDisplay(language = toLanguage)
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = toText,
      color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
      modifier = Modifier.align(Alignment.End)
    ) {
      IconButton(onClick = {
        onCopyClick(toText)
      }) {
        Icon(
          imageVector = Icons.Rounded.CopyAll,
          contentDescription = stringResource(MR.strings.copy),
          tint = LightBlue
        )
      }
      IconButton(onClick = onSpeakerClick) {
        Icon(
          imageVector = Icons.Rounded.Speaker,
          contentDescription = stringResource(MR.strings.play_loud),
          tint = LightBlue
        )
      }
    }
  }
}

@Composable
private fun IdleTranslateTextField(
  fromText: String,
  isTranslating: Boolean,
  onTextChange: (String) -> Unit,
  onTranslateClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  var isFocused by remember {
    mutableStateOf(false)
  }
  Box(modifier = modifier) {
    BasicTextField(
      value = fromText,
      onValueChange = onTextChange,
      cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
      modifier = Modifier
        .fillMaxSize()
        .onFocusChanged { isFocused = it.isFocused },
      textStyle = TextStyle(
        color = MaterialTheme.colorScheme.onSurface
      )
    )
    if (fromText.isEmpty() && !isFocused) {
      Text(
        text = stringResource(MR.strings.enter_a_text_to_translate),
        color = LightBlue
      )
    }
    ProgressButton(
      text = stringResource(MR.strings.translate),
      isLoading = isTranslating,
      onClick = onTranslateClick,
      modifier = Modifier.align(Alignment.BottomEnd)
    )
  }
}