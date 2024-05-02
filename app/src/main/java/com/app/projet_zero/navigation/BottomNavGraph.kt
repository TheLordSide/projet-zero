package com.app.projet_zero.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.projet_zero.model.BottomMenuData
import com.app.projet_zero.screen.HomeScreen
import com.app.projet_zero.screen.ProfileScreen


@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues){
    NavHost(
        navController = navController,
        startDestination = BottomMenuData.Home.route ,
        modifier = Modifier.padding(paddingValues),
    ) {
        composable(route = BottomMenuData.Home.route){
            HomeScreen()
        }
        composable(route = BottomMenuData.Profile.route){
            ProfileScreen()
        }
    }
}
