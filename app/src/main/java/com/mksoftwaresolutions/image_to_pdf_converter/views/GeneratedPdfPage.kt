package com.mksoftwaresolutions.image_to_pdf_converter.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GeneratedPdfPage(innerPadding: PaddingValues) {
    Surface(modifier = Modifier.padding(innerPadding).background(color = Color.Red).fillMaxSize()) {
        Text("Hello world this is the generated pdf list.")
    }
}