package com.example.bsafes

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.MimeTypeMap
import android.webkit.ServiceWorkerClient
import android.webkit.ServiceWorkerController
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebViewAssetLoader
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URI
import java.nio.ByteBuffer

class myPathHandler(context: Context) : WebViewAssetLoader.PathHandler {
    private val assetManager: AssetManager = context.getAssets()
    override fun handle(path: String): WebResourceResponse {
        val extension: String = MimeTypeMap.getFileExtensionFromUrl(path)
        val mimeType: String?
        if(extension == "js") {
            mimeType = "text/javascript"
        } else {
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        try {
            val response = WebResourceResponse(mimeType, "UTF-8", assetManager.open(path))
            return response
        } catch(e: Exception) {
            return WebResourceResponse("text/html", "UTF-8", assetManager.open("404.html"))
        }
    }
}
const val LOCAL_HOST = "https://android.bsafes.com"
const val PAGE_URL = LOCAL_HOST+ "/logIn.html"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun MyWebView() {
    var webView = remember { mutableStateOf<WebView?>(null) }
    var myFilePathCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }
    val requiredMimeType = remember { mutableStateOf<Array<String>>(arrayOf())}
    val downloadedFileUri = remember { mutableStateOf<Uri?>(null)}
    val arrayOfByteArrarys = remember { mutableStateOf<Array<ByteArray?>?>(null)}
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    val hideModalBotttomSheet: () -> Unit = {
        coroutineScope.launch { bottomSheetState.hide() }.invokeOnCompletion {
            if(!bottomSheetState.isVisible){
                openBottomSheet = false
            }
        }
    }

    val myWebChromeClient = object: WebChromeClient() {
        override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
            Log.d("WebView", consoleMessage.message())
            return true
        }

        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            Log.d("WebView", fileChooserParams?.mode.toString())
            Log.d("WebView", fileChooserParams?.acceptTypes.toString())

            if(fileChooserParams?.acceptTypes?.isNotEmpty() == true) {
                val mimeTypes =
                    if (fileChooserParams.acceptTypes.size == 1 && fileChooserParams.acceptTypes[0] == ""
                    ) arrayOf("*/*") else fileChooserParams.acceptTypes!!
                requiredMimeType.value = mimeTypes
                myFilePathCallback = filePathCallback
                openBottomSheet = true
                return true
            } else return false
        }
    }
    val afterPermissionGrantAction = remember {mutableStateOf<(() -> Unit)?>(null)}
    val writeExternalStoragePermission = rememberPermissionState(
        permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        onPermissionResult = {result->
            if(result) {
                afterPermissionGrantAction.value?.let {action -> action()}
            }
            afterPermissionGrantAction.value = null
        }
    )
    val contentResolver = LocalContext.current.contentResolver
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        val uri = data?.data
        val uriString = uri.toString()
        downloadedFileUri.value = uri
        webView.value?.evaluateJavascript("window.bsafesAndroid.downloadAnAttachmentOnAndroid(`$uriString`)", null)
        //webView.value?.evaluateJavascript("alert('hello')", null)
    }
    fun createNewFileIntent(fileName: String, fileType:String): Intent {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply{
            addCategory(Intent.CATEGORY_OPENABLE)
            type = fileType
            putExtra(Intent.EXTRA_TITLE, fileName )
        }
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        return intent
    }

    class MyJavascriptInterface(private val context:Context) {
        @JavascriptInterface
        fun getDownloadedFileUri(fileName:String, fileType: String) {
            val intent = createNewFileIntent(fileName, fileType)
            fileLauncher.launch(intent)
        }
        @JavascriptInterface
        fun addAChunkToFile(chunkIndex: Int, numberOfChunks: Int, string: String, uriString: String) {
            if(chunkIndex == 0) {
                arrayOfByteArrarys.value = arrayOfNulls(numberOfChunks)
            }
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    val stringLength = string.length
                    val thisByteArrary = string.toByteArray(Charsets.ISO_8859_1)
                    /*val buffer = ByteBuffer.allocate(stringLength)
                    for(i in 0..stringLength-1) {
                        buffer.put(string[i].code.toByte())
                    }
                    val bytes = buffer.array()*/
                    arrayOfByteArrarys.value!![chunkIndex] = thisByteArrary
                    if(chunkIndex == numberOfChunks-1) {
                        val uri = Uri.parse(uriString)
                        uri.let {
                            contentResolver.openOutputStream(it)?.let { outputStream ->
                                for (i in 0..numberOfChunks - 1) {
                                    val bytes = arrayOfByteArrarys.value!![i]
                                    outputStream.write(bytes)
                                }
                                outputStream.flush()
                                outputStream.close()
                            }
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    if(chunkIndex == numberOfChunks-1) {
                        Toast.makeText(context, "File saved!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            /*if(chunkIndex == numberOfChunks-1){
                val uri = Uri.parse(uriString)
                uri.let {
                    contentResolver.openOutputStream(it)?.let { outputStream ->
                        coroutineScope.launch {
                            withContext(Dispatchers.IO) {
                                Log.d("WebView", "outputStream: $outputStream")
                                for(i in 0..numberOfChunks-1) {
                                    val bytes = arrayOfByteArrarys.value!![i]
                                    outputStream.write(bytes)
                                }
                                outputStream.flush()
                                outputStream.close()
                            }
                        }
                    }
                }
            }*/
        }

        @JavascriptInterface
        fun saveBinaryStringAsFile(string: String, uriString: String) {
           /* fun saveToFile() {
                val stringLength = string.length
                Toast.makeText(context, "string length: $stringLength", Toast.LENGTH_SHORT).show()
            }
            if(writeExternalStoragePermission.status.isGranted) {
                saveToFile()
            } else {
                afterPermissionGrantAction.value = ::saveToFile
                writeExternalStoragePermission.launchPermissionRequest()
            }*/
            val uri = Uri.parse(uriString)
            uri.let {
                contentResolver.openOutputStream(it)?.let { outputStream ->
                    coroutineScope.launch {
                        withContext(Dispatchers.IO) {
                            Log.d("WebView", "outputStream: $outputStream")
                            val stringLength = string.length
                            val buffer = ByteBuffer.allocate(stringLength)
                            for(i in 0..stringLength-1) {
                                buffer.put(string[i].code.toByte())
                            }
                            val bytes = buffer.array()
                            outputStream.write(bytes)
                            outputStream.flush()
                            outputStream.close()
                        }
                    }
                }
            }
        }
    }
    AndroidView(factory = { context ->
        val assetLoader = WebViewAssetLoader.Builder()
            .setDomain("android.bsafes.com")
            .addPathHandler("/", myPathHandler(context))
            .build()
        val swController = ServiceWorkerController.getInstance()
        val serviceWorkerClient = object: ServiceWorkerClient() {
            override fun shouldInterceptRequest(request: WebResourceRequest): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(request.url)
            }
        }
        swController.setServiceWorkerClient(serviceWorkerClient)
        val serviceWorkerWebSettings = swController.serviceWorkerWebSettings
        serviceWorkerWebSettings.allowContentAccess = true
        serviceWorkerWebSettings.allowFileAccess  = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        return@AndroidView WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(false)
            webViewClient = object: WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest
                ): WebResourceResponse? {
                    val interceptedWebResponse = assetLoader.shouldInterceptRequest(request.url)
                    interceptedWebResponse?.let {
                        if(request.url.toString().endsWith("js", true)) {
                            it.mimeType = "text/javascript"
                        }
                    }
                    return interceptedWebResponse
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    request?.url?.host?.let { targetDomain ->
                        val currentDomain = URI(view?.url).toURL().host
                        if(targetDomain.equals(currentDomain)) {
                            return false
                        }
                        if(targetDomain.equals("www.bsafes.com") || targetDomain.equals("v2.bsafes.com")) {
                            val url = LOCAL_HOST + request.url?.path
                            view?.loadUrl(url)
                            return true
                        } else {
                            val intent = Intent(Intent.ACTION_VIEW, request.url)
                            try {
                                context.startActivity(intent)
                            } catch(exception: ActivityNotFoundException) {
                                Log.d("WebView", "Failed to load url ${request.url}")
                            }
                            return true
                        }
                        return false
                    }
                    return false
                }
            }
            webChromeClient = myWebChromeClient
        }
    }, update = {
        webView.value = it
        it.loadUrl(PAGE_URL)
        it.addJavascriptInterface(MyJavascriptInterface(it.context), "Android")
        if(writeExternalStoragePermission.status.isGranted) {
            Log.d("WebView", "Permission is granted")
        } else {
            //afterPermissionGrantAction.value = ::saveToFile
            Log.d("WebView", "Permission is not granted")
            writeExternalStoragePermission.launchPermissionRequest()
        }
        /*
            it.setDownloadListener({ url, userAgent, contentDisposition, mimeType, contentLength ->
                val request = DownloadManager.Request(Uri.parse(url))
                request.setMimeType(mimeType)
                request.addRequestHeader("cookie", android.webkit.CookieManager.getInstance().getCookie(url))
                request.addRequestHeader("User-Agent", userAgent)
                request.setDescription("Downloading file...")
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType))
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalFilesDir(it.context, Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType))
                val dm = it.context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                dm.enqueue(request)
                Toast.makeText(it.context, "Downloading File", Toast.LENGTH_LONG).show()
            })*/
    })
    if(openBottomSheet) {
        MediaChooser(
            sheetState = bottomSheetState,
            onDismissRequest = {
                myFilePathCallback?.onReceiveValue(null)
                openBottomSheet = false
            },
            onActionRequest = { value ->
                myFilePathCallback?.onReceiveValue(value)
                hideModalBotttomSheet()
            },
            requiredMimeType = requiredMimeType.value
        )
    }
}