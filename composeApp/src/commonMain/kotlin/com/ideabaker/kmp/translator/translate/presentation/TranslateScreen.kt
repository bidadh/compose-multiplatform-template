package com.ideabaker.kmp.translator.translate.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.ideabaker.kmp.translator.translate.presentation.components.LanguageDropDown
import com.ideabaker.kmp.translator.translate.presentation.components.SwapLanguagesButton
import com.ideabaker.kmp.translator.translate.presentation.components.TranslateTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TranslateScreen(
  state: TranslateState,
  onEvent: (TranslateEvent) -> Unit
) {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        LanguageDropDown(
          language = state.fromLanguage,
          isOpen = state.isChoosingFromLanguage,
          onClick = {
            onEvent(TranslateEvent.OpenFromLanguageDropDown)
          },
          onDismiss = {
            onEvent(TranslateEvent.StopChoosingLanguage)
          },
          onSelectLanguage = {
            onEvent(TranslateEvent.ChooseFromLanguage(it))
          }
        )
        Spacer(modifier = Modifier.weight(1f))
        SwapLanguagesButton(onClick = {
          onEvent(TranslateEvent.SwapLanguages)
        })
        Spacer(modifier = Modifier.weight(1f))
        LanguageDropDown(
          language = state.toLanguage,
          isOpen = state.isChoosingToLanguage,
          onClick = {
            onEvent(TranslateEvent.OpenToLanguageDropDown)
          },
          onDismiss = {
            onEvent(TranslateEvent.StopChoosingLanguage)
          },
          onSelectLanguage = {
            onEvent(TranslateEvent.ChooseToLanguage(it))
          }
        )
      }
    }
    item {
      val clipboardManager = LocalClipboardManager.current
      val keyboardController = LocalSoftwareKeyboardController.current
      TranslateTextField(
        fromText = state.fromText,
        toText = state.toText,
        isTranslating = state.isTranslating,
        fromLanguage = state.fromLanguage,
        toLanguage = state.toLanguage,
        onTranslateClick = {
          keyboardController?.hide()
          onEvent(TranslateEvent.Translate)
        },
        onTextChange = {
          onEvent(TranslateEvent.ChangeTranslationText(it))
        },
        onCopyClick = { text ->
          clipboardManager.setText(
            buildAnnotatedString {
              append(text)
            }
          )
        },
        onCloseClick = {
          onEvent(TranslateEvent.CloseTranslation)
        },
        onSpeakerClick = {

        },
        onTextFieldClick = {
          onEvent(TranslateEvent.EditTranslation)
        },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}