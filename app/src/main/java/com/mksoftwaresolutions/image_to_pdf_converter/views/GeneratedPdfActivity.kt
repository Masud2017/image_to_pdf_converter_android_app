package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mksoftwaresolutions.image_to_pdf_converter.R
import com.mksoftwaresolutions.image_to_pdf_converter.ui.theme.Image_to_pdf_converterTheme

class GeneratedPdfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Image_to_pdf_converterTheme {
                Scaffold {
                    innerPadding ->
                    GeneratedPdfPage(innerPadding)
                }
            }
        }
    }
}