package com.mksoftwaresolutions.image_to_pdf_converter

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mksoftwaresolutions.image_to_pdf_converter.ui.theme.Image_to_pdf_converterTheme
import com.mksoftwaresolutions.image_to_pdf_converter.views.HomePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Image_to_pdf_converterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomePage(innerPadding)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater:MenuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation_menu,menu)
//        return super.onCreateOptionsMenu(menu
        return true
    }
}
