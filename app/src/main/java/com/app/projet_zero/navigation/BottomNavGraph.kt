package com.app.projet_zero.navigation
import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.projet_zero.data.BottomMenuData
import com.app.projet_zero.screen.HomeScreen
import com.app.projet_zero.screen.LibraryScreen
import com.app.projet_zero.screen.ProfileScreen


@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues, context: Context) {


    NavHost(
        navController = navController,
        startDestination = BottomMenuData.Home.route,
        modifier = Modifier.padding(paddingValues),
    ) {
        composable(route = BottomMenuData.Home.route) {
            HomeScreen()
        }
        composable(route = BottomMenuData.Library.route) {
            LibraryScreen(context)
        }
        composable(route = BottomMenuData.Profile.route) {
            ProfileScreen()
        }
    }
}
