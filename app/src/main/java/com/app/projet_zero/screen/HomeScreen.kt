package com.app.projet_zero.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
            .fillMaxSize()


    ){
        Text(
            text = "Home Screen",
            color = Color.Black,
        )
    }
}

