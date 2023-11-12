package com.ideabaker.kmp.translator.screen.pin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ideabaker.kmm.translator.shared.MR
import com.ideabaker.kmp.translator.screen.Routes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PinScreen(onNavigate: (Routes) -> Unit) {
  Column(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxWidth()
      .padding(20.dp)
  ) {
    ClickableText(
      text = AnnotatedString(stringResource(MR.strings.back_to_login)),
      onClick = { onNavigate(Routes.Login) },
      style = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily.Default,
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.primary
      )
    )
  }
}
