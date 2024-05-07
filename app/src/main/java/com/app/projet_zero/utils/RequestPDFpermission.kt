package com.app.projet_zero.utils

import PDFLoader
import android.Manifest
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable

//fun PDFPermissionRequest(context: Context) {
//    val permissionState = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
//
//    val chooseFileLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            // Utilisez la fonction loadPDF pour charger le PDF
//            val inputStream = uri?.let { PDFLoader.loadPDF(context, it) }
//            // Faites quelque chose avec l'inputStream du PDF chargé
//        }
//
//    LaunchedEffect(permissionState) {
//        if (permissionState.status.isGranted) {
//            // Si la permission est accordée, lancer la sélection de fichier PDF
//            chooseFileLauncher.launch("application/pdf")
//        } else {
//            // Demander la permission si elle n'est pas accordée
//            permissionState.launchPermissionRequest()
//        }
//    }
//
//    LaunchedEffect(permissionState) {
//        if (permissionState.status.isGranted) {
//            // Si la permission est refusée, afficher un message d'erreur ou prendre une autre action
//            // Par exemple, afficher un Toast
//            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
//        }
//    }
//}


fun PDFPermissionRequest(context: Context) {
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

    // Demander la permission au lancement de la fonction
    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
}

