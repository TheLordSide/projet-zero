package com.app.projet_zero.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.projet_zero.model.BottomMenuData


@Composable
fun HomeBottomMenu() {
    val items = listOf(
        BottomMenuData.Home,
        BottomMenuData.Add,
        BottomMenuData.Profile
    )

    BottomNavigation(){
        items.forEach{
            BottomNavigationItem(
                label = { Text(text = it.title, fontSize = 9.sp) },
                alwaysShowLabel = true,
                selected = false,
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = it.icon,
                    contentDescription =  null,
                    modifier = Modifier.size(25.dp)
                       )})
        }

    }

}
