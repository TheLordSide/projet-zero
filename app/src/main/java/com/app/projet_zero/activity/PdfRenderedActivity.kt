package com.app.projet_zero.activity


import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.app.projet_zero.activity.ui.theme.Projet_zeroTheme
import com.rajat.pdfviewer.PdfRendererView
import com.rajat.pdfviewer.compose.PdfRendererViewCompose

class PdfRenderedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Récupérez l'Intent et l'URI
        val pdfUri = intent?.getStringExtra("pdf_uri")

        pdfUri?.let {
            Log.d("pdfUri", it)
            val uri = Uri.parse(it)
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


@Composable
fun MyPdfScreenFromUri(modifier: Modifier = Modifier, initialUri: Uri?) {
    val uri by rememberSaveable { mutableStateOf(initialUri) }

    Column(modifier = modifier) {
        AnimatedContent(
            targetState = uri,
            label = "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) { initialUri ->
            if (initialUri != null) {
                MyPdfRenderer(
                    uri = initialUri,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}

@Composable
fun MyPdfRenderer(uri: Uri, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    PdfRendererViewCompose(
        modifier = modifier,
        uri = uri,
        lifecycleOwner = lifecycleOwner,
        statusCallBack = object : PdfRendererView.StatusCallBack {
            override fun onPdfLoadStart() {
                Log.i("statusCallBack", "onPdfLoadStart")
            }

            override fun onPdfLoadProgress(
                progress: Int,
                downloadedBytes: Long,
                totalBytes: Long?
            ) {
                // Download is in progress
            }

            override fun onPdfLoadSuccess(absolutePath: String) {
                Log.i("statusCallBack", "onPdfLoadSuccess")
            }

            override fun onError(error: Throwable) {
                Log.i("statusCallBack", "onError")
            }

            override fun onPageChanged(currentPage: Int, totalPage: Int) {
                // Page change. Not required
            }
        }
    )
}