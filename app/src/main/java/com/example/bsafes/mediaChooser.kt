package com.example.bsafes

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MediaChooser(
    sheetState: SheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit = {},
    onActionRequest: (Array<Uri>) -> Unit = {},
    requiredMimeType: Array<String> = arrayOf()
) {
    val TAG = "mediaChooser"
    val context = LocalContext.current
    val mediaUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val handleSelectedUri: (List<Uri>) -> Unit = { selectedUri ->
        Log.d(TAG, "File selected = $selectedUri")
        onActionRequest(selectedUri.toTypedArray())
    }
    val visualMediaPickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia()) { selectedUri ->
        handleSelectedUri(selectedUri)
    }
    val authority = "com.bsafes.android.file_provider"
    fun createFile(mediaExtension: String): Uri {
        val directory = File(context.cacheDir, "captured_media")
        directory.mkdirs()
        val file = File.createTempFile(
            "media_${System.currentTimeMillis()}",
            ".${mediaExtension}",
            directory
        )
        return FileProvider.getUriForFile(context, authority, file)
    }
    val afterPermissionGrantAction = remember {mutableStateOf<(() -> Unit)?>(null)}
    val cameraPermission = rememberPermissionState(
        permission = android.Manifest.permission.CAMERA,
        onPermissionResult = { result ->
            if(result) {
                afterPermissionGrantAction.value?.let {action -> action()}
            }
            afterPermissionGrantAction.value = null
        }
    )
    val cameraLauncherForImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()) {
        if(it) {
            Log.d(TAG, "Image captured")
            onActionRequest(arrayOf(mediaUri.value!!))
        } else {
            Log.d(TAG, "Image not captured")
            onDismissRequest()
        }
    }
    fun launchCameraForImage() {
        mediaUri.value = createFile("jpg")
        cameraLauncherForImage.launch(mediaUri.value!!)
    }
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
                        visualMediaPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.SingleMimeType(
                                    requiredMimeType[0]
                                )
                            )
                        )
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
                        if(requiredMimeType[0].contains("image/")) {
                            if(cameraPermission.status.isGranted) {
                                launchCameraForImage()
                            } else {
                                afterPermissionGrantAction.value = ::launchCameraForImage
                                cameraPermission.launchPermissionRequest()
                            }
                        }
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