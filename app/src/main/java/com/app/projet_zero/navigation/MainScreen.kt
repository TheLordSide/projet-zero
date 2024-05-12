package com.app.projet_zero.navigation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
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
import com.app.projet_zero.dbHelpers.DBHandler
import java.io.File


@ExperimentalMaterial3Api
@Composable
fun MainScreen(){
    var topBarTitle by remember {mutableStateOf("Home")}
    var topBarIcon by remember { mutableStateOf(Icons.Default.Home) }

    val context = LocalContext.current

    val navController = rememberNavController()

    // Fonction de rafraîchissement de la liste des PDF
    fun refreshPdfList() {
        // Rafraîchir la liste des PDF
        navController.navigate(BottomMenuData.Library.route)
    }

    val chooseFileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            // Obtenez le nom du document à partir de l'URI
            val documentName = getFileNameFromUri(context, it)
            val documentSize = getFileSizeInKBFromUri(context, it)
            val dbHandler = DBHandler(context)
            dbHandler.addPDF(documentName, documentSize.toString(), uri.toString())

            // Appel de la fonction de rafraîchissement après l'ajout de la nouvelle entrée
            refreshPdfList()
        }

        // Reste du code pour charger le PDF et obtenir des informations sur le document
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Si la permission est accordée, lancer la sélection de fichier PDF
                chooseFileLauncher.launch("application/pdf")

            } else {
                // Si la permission est refusée, afficher un message d'erreur ou prendre une autre action
                // Par exemple, afficher un Toast
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }

        }

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
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                            } else {
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More",
                                modifier = Modifier.size(30.dp)
                            )

                        }
                    }
                }


            )
        },


        ) {paddingValues ->
        BottomNavGraph(
            // pour regler le probleme de padding value, jai
            // ajouter le paddingValues en parametres
            // de la fonction navController
            navController = navController,paddingValues, context = LocalContext.current

        )
    }
}


@SuppressLint("Range")
private fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var name: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        it.moveToFirst()
        name = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}

@SuppressLint("Range")
private fun getFileSizeInKBFromUri(context: Context, uri: Uri): Long {
    var size: Long = 0
    val file = File(uri.path.toString())
    if (file.exists()) {
        size = file.length() / 1024 // Convertir en KB
    } else {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val sizeIndex = it.getColumnIndex(OpenableColumns.SIZE)
            if (sizeIndex != -1) {
                val sizeBytes = it.getLong(sizeIndex)
                size = sizeBytes / 1024 // Convertir en KB
            }
        }
    }
    return size
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