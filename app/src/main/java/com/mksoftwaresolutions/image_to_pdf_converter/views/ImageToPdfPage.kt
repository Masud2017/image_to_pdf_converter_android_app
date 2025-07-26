package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AlertDialog
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.mksoftwaresolutions.image_to_pdf_converter.R
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.CameraPreviewViewModel
import com.mksoftwaresolutions.image_to_pdf_converter.viewmodels.ImageToPdfViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

var rewardedAd:RewardedAd? = null
fun loadRewardedAd(context:Context) {
    RewardedAd.load(
        context,
        "ca-app-pub-3940256099942544/5224354917",
        AdRequest.Builder().build(),
        object : RewardedAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
            }



        },
    )
}



@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ImageToPdfPage(innerPadding:PaddingValues,
                   imageToPdfViewModel: ImageToPdfViewModel,
                   cameraPreviewViewModel: CameraPreviewViewModel,
                   cameraController:LifecycleCameraController,context:Context) {
    var pdfGenerationDone:MutableState<Boolean>  = remember {
        mutableStateOf(false)
    }
    var shouldCameraOpen:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    var doesCameraHasPermission:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    var imageList:MutableList<Bitmap> = mutableListOf()


    //loading the rewarded ad
    loadRewardedAd(context)
    // loading the rewarded ad section finished


    rememberCoroutineScope().launch {
        imageToPdfViewModel.pdfGenerationDone.collectLatest {
            vall ->
            pdfGenerationDone.value = vall
        }
    }

    val context = LocalContext.current
    val lifeCycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)


    if (imageList.size > 0) {
        doesCameraHasPermission.value = false
        shouldCameraOpen.value = false
    }

    if (doesCameraHasPermission.value) {
        CameraPreviewContent(lifecycleOwner = lifeCycleOwner,
            modifier = Modifier.fillMaxSize(),
            viewModel = cameraPreviewViewModel,
            cameraController = cameraController,
            capturedImageList = imageList)
    } else {

        Surface(modifier= Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxSize()
                .background(color = Color(0xFFFFF7F7))
                .padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AndroidView(factory = {
                        ctx ->
                        AdView(ctx).apply {
                            setAdSize(AdSize.BANNER)
                            adUnitId= ContextCompat.getString(ctx,R.string.banner_ad_unit_id)
                            loadAd(AdRequest.Builder().build())
                        }
                    })

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth().height(300.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Card(elevation = CardDefaults.cardElevation(10.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                        Box(modifier = Modifier.height(100.dp).width(100.dp).padding(5.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()) {
                                Image(painter = painterResource(R.drawable.ic_camera_icon), contentDescription = "ic_camera_icon", modifier = Modifier.height(40.dp).width(40.dp))
                                Spacer(modifier = Modifier.height(5.dp))
                                Text("Image To Pdf Converter", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Card(elevation = CardDefaults.cardElevation(10.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                        Box(modifier = Modifier.height(100.dp).width(100.dp).padding(5.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()) {
                                Image(painter = painterResource(R.drawable.ic_camera_icon), contentDescription = "ic_camera_icon", modifier = Modifier.height(40.dp).width(40.dp))
                                Spacer(modifier = Modifier.height(5.dp))
                                Text("Image To Pdf Converter", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                }


                Column(modifier = Modifier.fillMaxWidth()) {
                    // heading
                    Surface(shadowElevation = 5.dp) {
                        Row(modifier = Modifier.fillMaxWidth().padding(3.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("All", fontWeight = FontWeight.Bold)
                            IconButton(onClick = {
                                // here we have to start the maximize window activity
                            }) {
                                Icon(Icons.Rounded.AccountBox, contentDescription = "AccountBox")
                            }
                        }
                    }
                    // heading section ended
                }




                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    Column {
                        if (pdfGenerationDone.value) {
                            ElevatedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                                Text("View")
                            }
                        }
                        ElevatedButton(onClick = {

                            rewardedAd?.show(context as Activity, OnUserEarnedRewardListener {
                                imageToPdfViewModel.finishGeneration()
                            })

//                            imageToPdfViewModel.finishGeneration()

                        }, modifier = Modifier.fillMaxWidth()) {
                            Text("Generate")
                        }

                        ElevatedButton(onClick =  {
                            var alertBuilder:AlertDialog.Builder = AlertDialog.Builder(
                                context
                            )

                            alertBuilder.setTitle("Plesae choose and option:")
                            alertBuilder.setIcon(R.drawable.ic_camera_icon)
                            alertBuilder.setMessage("YOYO")

                            var alertDialog = alertBuilder.create()
                            alertDialog.show()

                        }, modifier = Modifier.fillMaxWidth()) {
                            Text("Pick image")
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
                modifier = Modifier.size(70.dp).clickable {
                    // it will mutate the view model and change the data.
                }, colorFilter = ColorFilter.tint(color = Color.White))
        }
    }
}
