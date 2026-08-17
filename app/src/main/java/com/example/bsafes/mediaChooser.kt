package com.example.bsafes

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MediaChooser(
    sheetState: SheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit = {},
    onActionRequest: (Array<Uri>) -> Unit = {},
    requiredMimeType: Array<String> = arrayOf(),
    openSnackbarForCameraPermission: () -> Unit
) {
    val logTag = "mediaChooser"
    val context = LocalContext.current
    val mediaUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val handleSelectedUri: (List<Uri>) -> Unit = { selectedUri ->
        Log.d(logTag, "File selected = $selectedUri")
        onActionRequest(selectedUri.toTypedArray())
    }
    val visualMediaPickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia()) { selectedUri ->
        handleSelectedUri(selectedUri)
    }
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()) { selectedUri ->
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
        onPermissionResult = { isGranted ->
            if(isGranted) {
                afterPermissionGrantAction.value?.let {action -> action()}
            } else {
                onDismissRequest()
            }
            afterPermissionGrantAction.value = null
        }
    )
    val cameraLauncherForImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()) {
        if(it) {
            Log.d(logTag, "Image captured")
            onActionRequest(arrayOf(mediaUri.value!!))
        } else {
            Log.d(logTag, "Image not captured")
            onDismissRequest()
        }
    }
    fun launchCameraForImage() {
        mediaUri.value = createFile("jpg")
        cameraLauncherForImage.launch(mediaUri.value!!)
    }
    val cameraLauncherForVideo = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()) {
        if(it) {
            Log.d(logTag, "Video captured")
            onActionRequest(arrayOf(mediaUri.value!!))
        } else {
            Log.d(logTag, "Video not captured")
            onDismissRequest()
        }
    }
    fun launchCameraForVideo() {
        mediaUri.value = createFile("mp4")
        cameraLauncherForVideo.launch(mediaUri.value!!)
    }
    val size = 80.dp
    val coroutineScope = rememberCoroutineScope()
    fun checkAndRequestPermission() {
        if(cameraPermission.status.isGranted) {
            Log.d(logTag, "Camera permission granted")
            afterPermissionGrantAction.value?.let { action ->
                action()
            }
        } else {
            Log.d(logTag, "Requesting camera permission")
            if(cameraPermission.status.shouldShowRationale) {
                Log.d(logTag, "Show rational")
                openSnackbarForCameraPermission()
                onDismissRequest()
            } else {
                Log.d(logTag, "Request permission")
                val res = cameraPermission.launchPermissionRequest()
                Log.d(logTag, "Permission result = $res")
            }
        }
    }
    if(requiredMimeType[0].contains("image/") || requiredMimeType[0].contains("video/")) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            windowInsets = WindowInsets.safeDrawing
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom))
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
                            afterPermissionGrantAction.value = ::launchCameraForImage
                            checkAndRequestPermission()
                        } else {
                            afterPermissionGrantAction.value = ::launchCameraForVideo
                            checkAndRequestPermission()
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
        LaunchedEffect(requiredMimeType) {
            coroutineScope.launch {
                filePickerLauncher.launch(requiredMimeType)
            }
        }
    }
}