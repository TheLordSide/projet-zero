package com.app.projet_zero.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier
        .fillMaxSize()

        ){
        Text(
            text = "Profile Screen",
            color = Color.Black,
        )
    }
}

