package com.app.projet_zero.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.projet_zero.R
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme
import com.bls.compose_onboarding.OnBoardingScreen
import com.bls.compose_onboarding.data.OnBoardingPage


val onBoardingPages = listOf(
    OnBoardingPage(
        image = R.drawable.file,
        title = "Travel Compose",
        description = "Travel-compose is a library that helps you create onboarding screens in your app",
    ),
    OnBoardingPage(
        image = R.drawable.file,
        title = "How to use",
        description = "Just add the library to your project and use the OnBoardingScreen composable",
    ),
    OnBoardingPage(
        image = R.drawable.file,
        title = "Support me",
        description = "If you like the library, please star it on GitHub",
    ),
)



@Composable
fun OnboardScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White

    ) {
        OnBoardingScreen(
            skipText = "",
            items = onBoardingPages,
            onNextClicked = {


            }
        )

    }


}


@Composable
fun OnBoardItems(){


}


@Preview(showBackground = true)
@Composable
fun OnboardScreenPreview() {
    Projet_zeroTheme {
     //  OnboardScreen()
    }
}