@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.projet_zero.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme
import com.app.projet_zero.navigation.MainScreen
import com.app.projet_zero.onboard.OnboardScreen
//import com.app.projet_zero.screen.LibraryScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projet_zeroTheme {
                MainScreen()
                //LibraryScreen()
                
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