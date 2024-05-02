package com.app.projet_zero.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuData(
    val icon: ImageVector,
    val title : String,
    val route : String

){
    object Home: BottomMenuData(
        icon = Icons.Outlined.Home,
        title = "Home",
        route = "home")

    object Add: BottomMenuData(
        icon = Icons.Outlined.AddCircle,
        title = "Add",
        route = "add")

    object Profile: BottomMenuData(
        icon = Icons.Outlined.AccountCircle,
        title = "Profile",
        route = "profile")

}