package com.u1145h.kavitaandroid.feature.home

import android.app.Activity
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.u1145h.kavitaandroid.ui.components.OfflineScreen

/**
 * Root screen: the embedded Kavita web interface. Never looks like a browser —
 * no URL bar, no scrollbars, zoom disabled. The system back button walks the
 * web view history and only exits when there is no history.
 */
@Composable
fun HomeScreen(
    reloadTrigger: Int = 0,
    onOpenLibrary: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val serverUrl by viewModel.serverUrl.collectAsStateWithLifecycle()
    val bodyColor by viewModel.bridge.bodyColor.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val activity = context as? Activity

    var isLoading by rememberSaveable { mutableStateOf(true) }
    var isOffline by rememberSaveable { mutableStateOf(false) }
    var webView by remember { mutableStateOf<WebView?>(null) }

    var pendingFileCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
    ) { uris ->
        pendingFileCallback?.onReceiveValue(uris?.toTypedArray())
        pendingFileCallback = null
    }

    val onShowFileChooser: (WebChromeClient.FileChooserParams, ValueCallback<Array<Uri>>) -> Boolean =
        remember {
            { params, callback ->
                pendingFileCallback = callback
                fileLauncher.launch(params.acceptTypes.ifEmpty { arrayOf("*/*") })
                true
            }
        }

    BackHandler {
        val wv = webView
        if (wv != null && wv.canGoBack()) {
            wv.goBack()
        } else {
            activity?.finish()
        }
    }

    Box(modifier = modifier.fillMaxSize().background(Color(bodyColor))) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .statusBarsPadding(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
        ) {
            KavitaWebView(
                url = serverUrl,
                bridge = viewModel.bridge,
                modifier = Modifier.fillMaxSize(),
                reloadTrigger = reloadTrigger,
                onWebViewReady = { webView = it },
                onLoadingChanged = { isLoading = it },
                onOfflineChanged = { isOffline = it },
                onShowFileChooser = onShowFileChooser,
            )

            if (isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            if (isOffline) {
                OfflineScreen(
                    url = serverUrl,
                    onRetry = {
                        isOffline = false
                        webView?.reload()
                    },
                    onOpenSettings = onOpenSettings,
                )
            }
        }
    }
}
