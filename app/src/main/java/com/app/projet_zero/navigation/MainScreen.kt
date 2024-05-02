package com.app.projet_zero.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.projet_zero.model.BottomMenuData

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar ={
            BottomBar(
                navController = navController
            )
        }
    ) {paddingValues ->
        BottomNavGraph(
            // pour regler le probleme de padding value, jai
            // ajouter le paddingValues en parametres
            // de la fonction navController
            navController = navController,paddingValues

            )
    }
}



@Composable

fun BottomBar(navController: NavHostController){
    val screens  = listOf(
        BottomMenuData.Home,
        BottomMenuData.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )

        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomMenuData,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        label = {
            Text(
                text =
                screen.title
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {

         navController.navigate(screen.route){
           popUpTo(navController.graph.findStartDestination().id){
               saveState = true
           }
             launchSingleTop = true
             restoreState = true

         }


        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },


    )
}