package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mksoftwaresolutions.image_to_pdf_converter.ui.theme.Image_to_pdf_converterTheme
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.CameraPreviewViewModel
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.ImageToPdfViewModel

@Suppress("DEPRECATION")
class ImageToPdfActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Image_to_pdf_converterTheme {
                val imageToPdfViewModel:ImageToPdfViewModel by viewModels()
                val cameraPreviewViewModel:CameraPreviewViewModel by viewModels()
                val cameraController = LifecycleCameraController(
                    LocalContext.current)

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar()
                    }
                ) { innerPadding ->

                    ImageToPdfPage(innerPadding
                        ,imageToPdfViewModel,
                        cameraPreviewViewModel,
                        cameraController)
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    var context = LocalContext.current
    var expended:MutableState<Boolean>  = mutableStateOf(false)

    TopAppBar(
        title = { Text("Hello world") },
        actions = {
            IconButton(onClick = {

                expended.value = true
            }) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "MoreVert")
            }

            DropdownMenu(expanded = expended.value, onDismissRequest = {expended.value = false} ) {
                DropdownMenuItem(text = {
                        Row (horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically){
                            Icon(imageVector = Icons.AutoMirrored.Rounded.List, contentDescription = "List")
                            Text("All generated pdfs")
                        }
                    }, onClick = {
                    var intent = Intent(context, GeneratedPdfActivity::class.java)
                    context.startActivity(intent)
                })

                DropdownMenuItem(text = {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Icon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
                        Text("Share the app with your friends.")
                    }
                }, onClick = {})


            }

        }

    )
}