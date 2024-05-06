package com.app.projet_zero.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme
import java.io.File


@Composable

fun LibraryScreen() {
//    var pdfFiles by remember { mutableStateOf<List<File>>(emptyList()) }
//    val context = LocalContext.current
//
//    // Lanceur pour sélectionner les fichiers PDF
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
//        uri?.let {
//            val file = File(it.path.toString())
//            if (file.extension.equals("pdf", ignoreCase = true)) {
//                pdfFiles = pdfFiles.toMutableList().apply { add(file) }
//            }
//        }
//    }
//
//    Column {
//        Button(onClick = { launcher.launch(arrayOf("application/pdf")) }) {
//            Text("Sélectionner des PDF")
//        }
//
//        // Affichage des fichiers PDF dans une liste
//        PDFList(pdfFiles = pdfFiles)
//    }
//}
//
//// Composable pour afficher la liste des fichiers PDF
//@Composable
//fun PDFList(pdfFiles: List<File>) {
//    LazyColumn {
//        items(pdfFiles) { pdfFile ->
//            Text(pdfFile.name)
//            // Vous pouvez ajouter des fonctionnalités pour ouvrir les fichiers PDF ici
//        }
//    }
//}
}
@Preview
@Composable
fun LibraryScreenPreview() {
    Projet_zeroTheme {
        LibraryScreen()
    }
}
