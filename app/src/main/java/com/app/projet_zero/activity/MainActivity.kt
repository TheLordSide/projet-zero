package com.app.projet_zero.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme
import com.app.projet_zero.component.HomeBottomMenu
import com.app.projet_zero.navigation.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projet_zeroTheme {
                MainScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    Projet_zeroTheme {
        MainScreen()
    }
}