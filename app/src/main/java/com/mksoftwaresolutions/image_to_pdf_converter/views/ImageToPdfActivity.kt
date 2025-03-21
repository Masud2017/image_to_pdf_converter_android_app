package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mksoftwaresolutions.image_to_pdf_converter.ui.theme.Image_to_pdf_converterTheme
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.CameraPreviewViewModel
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.ImageToPdfViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Suppress("DEPRECATION")
class ImageToPdfActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Image_to_pdf_converterTheme {
                val imageToPdfViewModel:ImageToPdfViewModel by viewModels()
                val cameraPreviewViewModel:CameraPreviewViewModel by viewModels()
                val cameraController:LifecycleCameraController = LifecycleCameraController(
                    LocalContext.current)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    ImageToPdfPage(innerPadding
                        ,imageToPdfViewModel,
                        cameraPreviewViewModel,
                        cameraController)
                }
            }
        }
    }
}