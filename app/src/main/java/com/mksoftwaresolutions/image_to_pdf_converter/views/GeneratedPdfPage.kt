package com.mksoftwaresolutions.image_to_pdf_converter.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GeneratedPdfPage(innerPadding: PaddingValues) {


    Surface(modifier = Modifier.padding(innerPadding).background(color = Color.Red).fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()
            .background(color = Color.Red).padding(5.dp)) {
                LazyVerticalStaggeredGrid (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(15.dp), verticalItemSpacing = 15.dp, columns = StaggeredGridCells.Adaptive(150.dp)) {
                    items(10) {
                        ElevatedCard(onClick = {},
                            modifier = Modifier.height(250.dp).width(150.dp)) {
                            var enabled = remember { mutableStateOf(false) }

                            DropdownMenu(expanded = true, onDismissRequest = {enabled.value = false},Modifier.width(150.dp).height(50.dp)) {
                                DropdownMenuItem(onClick = {}, text =  {
                                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
                                        Text("Share the pdf.")
                                    }
                                })
                            }
                            Column(modifier = Modifier.fillMaxSize()) {

                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                    IconButton(onClick = {enabled.value = true}) { Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "MoreVert") }


                                }

                            }
                        }
                    }
                }
        }
    }
}

@Preview
@Composable
fun PrevGeneratedPdfPage() {
    GeneratedPdfPage(PaddingValues(0.dp))
}