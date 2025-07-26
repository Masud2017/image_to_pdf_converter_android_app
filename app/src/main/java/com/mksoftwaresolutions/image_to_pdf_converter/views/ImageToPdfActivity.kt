package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.karumi.dexter.BuildConfig
import com.mksoftwaresolutions.image_to_pdf_converter.R
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

                val context = LocalContext.current

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Surface(shadowElevation = 5.dp) {
                            TopBar(context)
                        }
                    }
                ) { innerPadding ->

                    ImageToPdfPage(innerPadding
                        ,imageToPdfViewModel,
                        cameraPreviewViewModel,
                        cameraController, LocalContext.current)
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(context: Context) {
    var context = LocalContext.current
    var expended:MutableState<Boolean>  = mutableStateOf(false)

    TopAppBar(
        title = { Text("Home", fontWeight = FontWeight.Bold) },
        actions = {
            // share button section started
            IconButton(onClick = {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
                )
                sendIntent.setType("text/plain")
                val shareChooser = Intent.createChooser(sendIntent,null)
                startActivity(context, sendIntent,null)
            }) {
                Icon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
            }
            // share button section ended

            // Premium button section started
            IconButton(onClick = {
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setMessage("Yo you don't need to be primium user!!")
                builder.setPositiveButton("Done", object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        TODO("Not yet implemented")
                    }
                })
                builder.show()
            }) {
                Image(painter = painterResource(R.drawable.crown), contentDescription = "crown")
            }
            // Premium button section ended

//            IconButton(onClick = {
//
//                expended.value = true
//            }) {
//                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "MoreVert")
//            }
//
//            DropdownMenu(expanded = expended.value, onDismissRequest = {expended.value = false} ) {
//                DropdownMenuItem(text = {
//                        Row (horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically){
//                            Icon(imageVector = Icons.AutoMirrored.Rounded.List, contentDescription = "List")
//                            Text("All generated pdfs")
//                        }
//                    }, onClick = {
//                    var intent = Intent(context, GeneratedPdfActivity::class.java)
//                    context.startActivity(intent)
//                })
//
//                DropdownMenuItem(text = {
//                    Row(verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
//                        Icon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
//                        Text("Share the app with your friends.")
//                    }
//                }, onClick = {})
//
//
//            }

        }

    )
}