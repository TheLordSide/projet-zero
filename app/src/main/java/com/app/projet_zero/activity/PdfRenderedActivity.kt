package com.app.projet_zero.activity


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme
import com.app.projet_zero.utils.MyPdfScreenFromUri
import com.app.projet_zero.utils.extractTextFromPdf
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader

class PdfRenderedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PDFBoxResourceLoader.init(applicationContext)
        // Récupérez l'Intent et l'URI
        val pdfUri = intent?.getStringExtra("pdf_uri")

        pdfUri?.let {
            Log.d("pdfUri", it)
            val uri = Uri.parse(it)
//             Extraire le texte du PDF
            val extractedText = extractTextFromPdf(this, uri)
            if (extractedText != null) {
                Log.d("ExtractedText", extractedText)
                Toast.makeText(this, "Texte extrait: $extractedText", Toast.LENGTH_LONG).show()
            } else {
                Log.e("PdfRenderedActivity", "Échec de l'extraction du texte")
            }
            setContent {
                Projet_zeroTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MyPdfScreenFromUri(
                            modifier = Modifier.fillMaxSize(),
                            initialUri = uri
                        )
                    }
                }
            }
        } ?: run {
            Log.e("PdfRenderedActivity", "No URI received")
        }
    }
}