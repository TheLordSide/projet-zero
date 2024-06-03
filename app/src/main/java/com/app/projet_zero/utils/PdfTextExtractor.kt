package com.app.projet_zero.utils

import android.content.Context
import android.net.Uri
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.InputStream

fun extractTextFromPdf(context: Context, pdfUri: Uri): String? {
    var text: String? = null
    var document: PDDocument? = null
    try {
        // Ouvrir le flux d'entrée pour lire le fichier PDF
        val inputStream: InputStream? = context.contentResolver.openInputStream(pdfUri)
        if (inputStream != null) {
            // Charger le document PDF
            document = PDDocument.load(inputStream)
            // Utiliser PDFTextStripper pour extraire le texte
            val pdfTextStripper = PDFTextStripper()
            text = pdfTextStripper.getText(document)
            // Truncate to the first 300 characters
            text = text?.take(300)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Fermer le document PDF pour libérer les ressources
        document?.close()
    }
    return text
}