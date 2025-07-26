package com.mksoftwaresolutions.image_to_pdf_converter.views

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mksoftwaresolutions.image_to_pdf_converter.R

@Composable
fun HomePage(innerPaddingParent:PaddingValues) {
    var context = LocalContext.current
    Scaffold { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().background(color = Color(0xFFFFF7F7)).padding(innerPadding)
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterHorizontally)
                ) {
                Card(modifier = Modifier.size(100.dp).padding(2.dp)) {
                    Image(painter = painterResource(R.drawable.image_ic),
                        contentDescription = "Something went wrong with the image")
                }

                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "ArrowForward", modifier = Modifier.size(50.dp))

                Card(modifier = Modifier.size(100.dp).padding(2.dp)) {
                    Image(painter = painterResource(R.drawable.adobe_svg_logo),
                        contentDescription = "Something went wrong with the image")
                }

            } // row
            Spacer(modifier = Modifier.size(50.dp))
            Button(onClick = {

                var activityIntent = Intent(context, ImageToPdfActivity::class.java)
                context.startActivity(activityIntent)
            }, colors = ButtonColors(containerColor = Color.White, contentColor = Color.Black, disabledContentColor = Color.White, disabledContainerColor = Color.Gray)) {
                Text("Get started")
            }
        }
    }
}

@Preview
@Composable
fun PrevHomePage() {
    HomePage(innerPaddingParent = PaddingValues(2.dp))
}

