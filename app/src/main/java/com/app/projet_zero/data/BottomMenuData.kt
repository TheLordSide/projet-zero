package com.app.projet_zero.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuData(
    val icon: ImageVector,
    val title : String,
    val route : String

){
    data object Home: BottomMenuData(
        icon = Icons.Outlined.Home,
        title = "Home",
        route = "home")

    data object Library: BottomMenuData(
        icon = Icons.Outlined.Place,
        title = "Library",
        route = "library")

    data object Profile: BottomMenuData(
        icon = Icons.Outlined.AccountCircle,
        title = "Profile",
        route = "profile")

}