package com.app.projet_zero.navigation

import PDFLoader
import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.projet_zero.data.BottomMenuData

@ExperimentalMaterial3Api
@Composable
fun MainScreen(){
    var topBarTitle by remember {mutableStateOf("Home")}
    var topBarIcon by remember { mutableStateOf(Icons.Default.Home) }
    val context = LocalContext.current
    val chooseFileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // Utilisez la fonction loadPDF pour charger le PDF
        val inputStream = uri?.let { PDFLoader.loadPDF(context, it) }
        // Faites quelque chose avec l'inputStream du PDF chargé
    }

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Si la permission est accordée, lancer la sélection de fichier PDF
            chooseFileLauncher.launch("application/pdf")
        } else {
            // Si la permission est refusée, afficher un message d'erreur ou prendre une autre action
            // Par exemple, afficher un Toast
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }

    }


    val navController = rememberNavController()
    Scaffold(
        bottomBar ={
            BottomBar(
                navController = navController,
                onMenuSelected = { menu ->
                    topBarTitle = when (menu) {
                        BottomMenuData.Home -> "Home"
                        BottomMenuData.Library -> "Library"
                        BottomMenuData.Profile -> "Profile"
                    }

                    topBarIcon = when (menu) {
                        BottomMenuData.Home -> Icons.Default.Home
                        BottomMenuData.Library -> Icons.Default.Add
                        BottomMenuData.Profile -> Icons.Default.Person
                    }
                }
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(text = topBarTitle, fontSize = 30.sp) },
                actions = {
                    if (topBarTitle == "Library") {
                        IconButton(onClick = {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                },

                )
        },
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

fun BottomBar(navController: NavHostController, onMenuSelected: (BottomMenuData) -> Unit){
    val screens  = listOf(
        BottomMenuData.Home,
        BottomMenuData.Library,
        BottomMenuData.Profile

    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                onMenuSelected = onMenuSelected
            )

        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomMenuData,
    currentDestination: NavDestination?,
    navController: NavHostController,
    onMenuSelected: (BottomMenuData) -> Unit
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
            onMenuSelected(screen)


        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },


        )
}