package com.example.bsafes

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.MimeTypeMap
import android.webkit.ServiceWorkerClient
import android.webkit.ServiceWorkerController
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebViewAssetLoader
import kotlinx.coroutines.launch
import java.net.URI

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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RestrictedApi")
@Composable
fun MyWebView() {
    var myFilePathCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }
    var requiredMimeType = remember { mutableStateOf<Array<String>>(arrayOf())}
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
                            val url = LOCAL_HOST + request?.url?.path
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
            it.loadUrl(PAGE_URL)
    })
    if(openBottomSheet) {
        MediaChosser(
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