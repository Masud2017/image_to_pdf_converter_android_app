package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.graphics.Bitmap
import android.media.ImageWriter
import androidx.annotation.OptIn
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Logger
import androidx.camera.core.internal.compat.ImageWriterCompat
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.CameraPreviewViewModel

@Composable
fun CameraPreviewContent(
    viewModel: CameraPreviewViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    cameraController:LifecycleCameraController,
    capturedImageList:MutableList<Bitmap>
) {
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    surfaceRequest?.let { request ->
        CameraXViewfinder(
            surfaceRequest = request,
            modifier = modifier
        )
        Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.BottomCenter) {
            Row {
                IconButton(onClick = {
                    cameraController.takePicture(ContextCompat.getMainExecutor(context),
                        object: OnImageCapturedCallback() {
                            override fun onCaptureStarted() {
                                super.onCaptureStarted()
                            }

                            @androidx.camera.core.ExperimentalGetImage
                            @OptIn(ExperimentalGetImage::class)
                            override fun onCaptureSuccess(image: ImageProxy) {
                                super.onCaptureSuccess(image)
                                capturedImageList.add(image.toBitmap())
                                println("New image has been saved ${image.image.height}")

                            }

                            override fun onError(exception: ImageCaptureException) {
                                super.onError(exception)
                            }

                            override fun onCaptureProcessProgressed(progress: Int) {
                                super.onCaptureProcessProgressed(progress)
                            }

                            override fun onPostviewBitmapAvailable(bitmap: Bitmap) {
                                super.onPostviewBitmapAvailable(bitmap)
                            }

                        })
                }) {
                    Icon(imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "AccountCircle",
                        modifier = Modifier.background(color = Color.White)
                        )
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.Refresh,
                        contentDescription = "Refresh")
                }
            }
        }
    }
}