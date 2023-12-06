package com.savchuk.app.blazegame.web

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        LinkWebView(url = "https://fex.net/")
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LinkWebView(url: String?, modifier: Modifier = Modifier) {
    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    var filePathCallback: ValueCallback<Array<Uri>>? = null


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                filePathCallback?.onReceiveValue(arrayOf(uri))
                filePathCallback = null
            }
        }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnabled = view.canGoBack()
                    }
                }
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true

                webChromeClient = object : WebChromeClient() {
                    override fun onShowFileChooser(
                        webView: WebView?,
                        filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?
                    ): Boolean {
                        launcher.launch(fileChooserParams?.createIntent())
                        return true
                    }
                }
                url?.let { loadUrl(it) }
                webView = this
            }
        }, update = {
            webView = it
        })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}
