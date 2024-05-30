package com.app.projet_zero.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.projet_zero.R
import com.app.projet_zero.activity.PdfRenderedActivity
import com.app.projet_zero.dbHelpers.DBHandler
import com.app.projet_zero.model.PdfModel

@Composable
fun LibraryScreen(context: Context) {
    var pdfList by remember { mutableStateOf<List<PdfModel>>(emptyList()) }
    var pdfToDelete by remember { mutableStateOf<PdfModel?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    val dbHandler = DBHandler(context)
    pdfList = dbHandler.pdfList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        itemsIndexed(pdfList) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val sUri = pdfList[index].uri
                        val uri = Uri.parse(sUri)
                        Log.d("Selected PDF", "URI: $sUri")
                        val intent = Intent(context, PdfRenderedActivity::class.java).apply {
                            putExtra("pdf_uri", uri.toString())  // Passez l'URI sous forme de String
                        }
                        context.startActivity(intent)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ajout de l'image à gauche du texte
                Image(
                    painter = painterResource(id = R.drawable.file),
                    contentDescription = "PDF Image",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(end = 5.dp),
                    contentScale = ContentScale.Fit
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = pdfList[index].pdfName,
                        fontSize = 14.sp,
                        color = Color.Unspecified
                    )
                    Text(
                        text = pdfList[index].pdfSize + " KB",
                        fontSize = 14.sp,
                        color = Color.Unspecified
                    )
                }
                IconButton(
                    onClick = {
                        pdfToDelete = pdfList[index]
                        showDialog = true
                    },
                    modifier = Modifier.size(50.dp)

                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete PDF",
                        tint = Color.Gray
                    )
                }
            }
        }
    }

    // Afficher le PDF sélectionné

    // Boîte de dialogue de confirmation pour la suppression
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                pdfToDelete = null
            },
            title = { Text(text = "Retirer le PDF ?") },
            text = { Text(text = "Êtes-vous sûr de vouloir retirer le PDF sélectionné ?") },

            confirmButton = {
                Button(
                    onClick = {
                        pdfToDelete?.let { pdf ->
                            dbHandler.deletePDF(pdf.id)
                            pdfList = dbHandler.pdfList()
                        }
                        showDialog = false
                        pdfToDelete = null
                    }
                ) {
                    Text(text = "Confirmer")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        pdfToDelete = null
                    }
                ) {
                    Text(text = "Annuler")
                }
            }
        )
    }
}
