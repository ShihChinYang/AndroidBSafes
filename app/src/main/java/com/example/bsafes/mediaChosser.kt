package com.example.bsafes

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaChosser(
    sheetState: SheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit = {},
    onActionRequest: (Array<Uri>) -> Unit = {},
    requiredMimeType: Array<String> = arrayOf()
) {
    val size = 80.dp
    val coroutineScope = rememberCoroutineScope()
    if(requiredMimeType[0].contains("image/")) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = onDismissRequest) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                Column {
                    IconButton(onClick = {

                    }, modifier = Modifier.size(size)){
                        Icon(
                            Image,
                            contentDescription = "Localized Description",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = "Gallery",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        // adding text align on below line.
                        textAlign = TextAlign.Center,
                        // adding font size on below line.
                        fontSize = 20.sp
                    )
                }
                Column {
                    IconButton(onClick = {

                    }, modifier = Modifier.size(size)){
                        Icon(
                            Photo_camera,
                            contentDescription = "Localized Description",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = "Camera",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        // adding text align on below line.
                        textAlign = TextAlign.Center,
                        // adding font size on below line.
                        fontSize = 20.sp
                    )
                }
            }
        }
    } else {

    }
}