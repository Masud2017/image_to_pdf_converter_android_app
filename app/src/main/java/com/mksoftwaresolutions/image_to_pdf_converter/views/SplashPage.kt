package com.mksoftwaresolutions.image_to_pdf_converter.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mksoftwaresolutions.image_to_pdf_converter.R
import com.mksoftwaresolutions.image_to_pdf_converter.ui.theme.Typography

@Composable
fun SplashPage() {
    Scaffold {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)
            .fillMaxSize()
            .background(color = Color.Red)
            .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.app_title), style = Typography.headlineLarge,
                color = Color.White, fontWeight = FontWeight(500)
            )
            Text("A friendly image to pdf converter", color = Color.White)
        }

        Box(modifier = Modifier.fillMaxSize().padding(15.dp),
            contentAlignment = Alignment.BottomEnd) {
            Text("Powered by MkSoftware Solutions", color = Color.White, style = Typography.bodyLarge)

        }
    }
}

@Preview
@Composable
fun PrevSplashPage() {
    SplashPage()
}