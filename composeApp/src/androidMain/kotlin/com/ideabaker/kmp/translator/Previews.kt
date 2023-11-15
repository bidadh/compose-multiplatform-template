package com.ideabaker.kmp.translator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ideabaker.kmm.translator.shared.MR
import com.ideabaker.kmp.translator.core.presentation.UiLanguage
import com.ideabaker.kmp.translator.screen.home.HomeScreen
import com.ideabaker.kmp.translator.screen.home.HomeViewModel
import com.ideabaker.kmp.translator.screen.login.LoginScreen
import com.ideabaker.kmp.translator.screen.login.LoginState
import com.ideabaker.kmp.translator.screen.pin.PinScreen
import com.ideabaker.kmp.translator.service.GreetingService
import com.ideabaker.kmp.translator.service.PlatformService
import com.ideabaker.kmp.translator.translate.presentation.components.LanguageDropDownItem
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@Preview
@Composable
fun ImagePreview() {
  val imageModifier = Modifier
    .size(150.dp)
    .border(BorderStroke(1.dp, Color.Black))
    .background(Color.Yellow)
  Image(
    painter = painterResource(MR.images.user_login),
    contentDescription = stringResource(MR.strings.login_title),
    contentScale = ContentScale.Fit,
    modifier = imageModifier
  )
}


@Preview
@Composable
fun LanguageDropDownItemPreview() {
  LanguageDropDownItem(
    language = UiLanguage.by("en"),
    onClick = { }
  )
}

@Preview(
  name = "Login Screen",
  showSystemUi = true,
  showBackground = false,
)
@Preview(
  device = "spec:parent=pixel_5,orientation=landscape"

)
@Composable
fun LoginScreenPreview() {
  val state = LoginState()
  LoginScreen(state, {}) {}
}

@Preview(
  name = "Home Screen",
  showSystemUi = true,
  showBackground = false,
)
@Composable
fun HomeScreenPreview() {
  HomeScreen(viewModel = HomeViewModel(GreetingService(PlatformService(LocalContext.current)))) { }
}

@Preview(
  name = "Pin Screen",
  showSystemUi = true,
  showBackground = false,
)
@Composable
fun PinScreenPreview() {
  PinScreen { }
}
