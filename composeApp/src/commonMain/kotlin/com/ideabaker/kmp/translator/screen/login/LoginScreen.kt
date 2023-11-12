package com.ideabaker.kmp.translator.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ideabaker.kmm.translator.shared.MR
import com.ideabaker.kmp.translator.screen.Routes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun LoginScreen(
  state: LoginState,
  onEvent: (LoginEvent) -> Unit,
  onNavigate: (Routes) -> Unit
) {
  Column(
    modifier = Modifier.padding(20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    val rainbowColorsBrush = remember {
      Brush.sweepGradient(
        listOf(
          Color(0xFF9575CD),
          Color(0xFFBA68C8),
          Color(0xFFE57373),
          Color(0xFFFFB74D),
          Color(0xFFFFF176),
          Color(0xFFAED581),
          Color(0xFF4DD0E1),
          Color(0xFF9575CD)
        )
      )
    }
    Image(
      painter = painterResource(MR.images.user_login),
      contentDescription = stringResource(MR.strings.login_title),
      modifier = Modifier
        .padding(0.dp, 20.dp, 0.dp, 20.dp)
        .clip(CircleShape)
        .border(
          BorderStroke(4.dp, rainbowColorsBrush),
          CircleShape
        )
        .size(120.dp)
    )

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
      label = { Text(text = stringResource(MR.strings.username)) },
      value = state.username,
      onValueChange = { onEvent(LoginEvent.UsernameChanged(it)) })

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
      label = { Text(text = stringResource(MR.strings.password)) },
      value = state.password,
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) })

    Spacer(modifier = Modifier.height(20.dp))
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
      Button(
        onClick = {
          onEvent(LoginEvent.Login { onNavigate(Routes.Translate) })
        },
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
      ) {
        Text(text = stringResource(MR.strings.login_button))
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
    ClickableText(
      text = AnnotatedString(stringResource(MR.strings.sign_up_link)),
      onClick = { onNavigate(Routes.Home) },
      style = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.primary
      )
    )
    if(state.error != null) {
      val message = if(state.error == LoginError.USERNAME_EMPTY) {
        stringResource(MR.strings.username_error)
      } else {
        stringResource(MR.strings.password_error)
      }
      Spacer(modifier = Modifier.height(20.dp))
      Text(
        text = message,
        style = TextStyle(
          fontSize = 14.sp,
          fontFamily = FontFamily.Default,
          color = MaterialTheme.colorScheme.error
        )
      )
    }

    this@Column.AnimatedVisibility(
      state.isLoading,
      enter = fadeIn(),
      exit = fadeOut()
    ) {
      CircularProgressIndicator()
    }
  }
}
