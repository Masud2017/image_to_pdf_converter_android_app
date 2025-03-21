package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mksoftwaresolutions.image_to_pdf_converter.R
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.ImageToPdfViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.CameraPreviewViewModel

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ImageToPdfPage(innerPadding:PaddingValues, imageToPdfViewModel: ImageToPdfViewModel,cameraPreviewViewModel: CameraPreviewViewModel) {
    var pdfGenerationDone:MutableState<Boolean>  = remember {
        mutableStateOf(false)
    }
    var shouldCameraOpen:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    var doesCameraHasPermission:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    rememberCoroutineScope().launch {
        imageToPdfViewModel.pdfGenerationDone.collectLatest {
            vall ->
            pdfGenerationDone.value = vall
        }
    }

    val context = LocalContext.current
    val lifeCycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)


    if (doesCameraHasPermission.value) {
        CameraPreviewContent(lifecycleOwner = lifeCycleOwner, modifier = Modifier.fillMaxSize(), viewModel = cameraPreviewViewModel)
    } else {

        Surface(modifier= Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxSize()
                .background(color = Color.Red)
                .padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.border(BorderStroke(8.dp, color = Color.White)).clip(
                    RoundedCornerShape(20.dp)
                ).padding(5.dp).
                height(250.dp).
                fillMaxWidth().
                clickable {
                    shouldCameraOpen.value = true

                    if (cameraPermissionState.status.isGranted) {
                        doesCameraHasPermission.value = true
                    } else {
                        if (cameraPermissionState.status.shouldShowRationale) {
                            Toast.makeText(context, "Yo you should better let me see you", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Yo you should better let me see you", Toast.LENGTH_LONG).show()
                        }

                        cameraPermissionState.launchPermissionRequest()
                    }
                }
                    , contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(R.drawable.image_ic), contentDescription = "Can not find any data named image_ic",Modifier.size(150.dp),
                        colorFilter = ColorFilter.tint(color = Color.White))
                }

                Spacer(modifier = Modifier.size(25.dp))
                AddedImageRow()

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    Column {
                        if (pdfGenerationDone.value) {
                            ElevatedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                                Text("View")
                            }
                        }
                        ElevatedButton(onClick = {
                            imageToPdfViewModel.finishGeneration()
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text("Generate")
                        }
                    }
                }

            }
        }
    }



}

@Composable
fun AddedImageRow() {
    LazyRow {
        items(10) {
            Image(painter = painterResource(R.drawable.image_ic), contentDescription = "image_Ic",
                modifier = Modifier.size(70.dp), colorFilter = ColorFilter.tint(color = Color.White))
        }
    }
}

//@Preview
//@Composable
//fun PrevImageToPdfPage() {
//
//    ImageToPdfPage(PaddingValues(0.dp),true)
//
//}