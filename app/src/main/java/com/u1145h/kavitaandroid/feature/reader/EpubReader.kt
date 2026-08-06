package com.u1145h.kavitaandroid.feature.reader

import android.graphics.Color
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val FONT_SIZES = floatArrayOf(16f, 18f, 20f, 24f, 28f)
private const val READER_BACKGROUND = 0xFF0E0E12.toInt()

/**
 * EPUB reader: unpacks the book, walks the spine in a swipeable pager of
 * WebViews, injecting a reflowable text style for comfortable offline reading.
 */
@Composable
fun EpubReader(
    filePath: String,
    initialHref: String?,
    onProgress: (Int, Float, String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val spine = remember(filePath) { EpubParser.extract(context, filePath) }
    DisposableEffect(spine) {
        onDispose { spine?.close() }
    }
    var fontIndex by remember { mutableStateOf(2) }

    if (spine == null || spine.pageCount == 0) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                "This EPUB could not be read",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        return
    }

    val initialIndex = initialHref?.let(spine::hrefIndex) ?: 0
    val pagerState = rememberPagerState(
        initialPage = initialIndex.coerceIn(0, spine.pageCount - 1),
    ) { spine.pageCount }

    LaunchedEffect(pagerState.currentPage) {
        val page = pagerState.currentPage
        val percent = ((page + 1).toFloat() / spine.pageCount * 100f).coerceIn(0f, 100f)
        onProgress(page, percent, spine.hrefs[page])
    }

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { it },
        ) { index ->
            SpinePageView(
                url = spine.spine[index].toURI().toString(),
                fontSize = FONT_SIZES[fontIndex],
            )
        }

        Surface(
            onClick = { fontIndex = (fontIndex + 1) % FONT_SIZES.size },
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shadowElevation = 4.dp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
        ) {
            Text(
                text = "Aa",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            )
        }

        Text(
            text = "${pagerState.currentPage + 1} / ${spine.pageCount}",
            style = MaterialTheme.typography.labelSmall,
            color = ComposeColor(0xFF9AA0A6),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 20.dp),
        )
    }
}

@Composable
private fun SpinePageView(
    url: String,
    fontSize: Float,
) {
    var styledHtml by remember(url) { mutableStateOf<String?>(null) }
    LaunchedEffect(url, fontSize) {
        styledHtml = withContext(Dispatchers.IO) {
            runCatching {
                val path = Uri.parse(url).path
                File(path).readText().let { injectCss(it, fontSize) }
            }.getOrNull()
        }
    }

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                setBackgroundColor(READER_BACKGROUND)
                settings.javaScriptEnabled = false
                settings.domStorageEnabled = true
                settings.setSupportZoom(false)
                settings.builtInZoomControls = false
                settings.displayZoomControls = false
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                webViewClient = WebViewClient()
            }
        },
        update = { view ->
            styledHtml?.let { html ->
                view.loadDataWithBaseURL(url, html, "text/html", "UTF-8", null)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(ComposeColor(READER_BACKGROUND)),
    )
}

private fun injectCss(html: String, fontSize: Float): String {
    val css = """
        <style type="text/css">
          html { -webkit-text-size-adjust: 100%; }
          body { margin: 0 !important; padding: 6vw 7vw !important;
                 font-family: Georgia, 'Times New Roman', serif !important;
                 font-size: ${fontSize}px !important;
                 color: #E4E1E9 !important; background: transparent !important;
                 word-wrap: break-word !important; }
          p { line-height: 1.65 !important; margin: 0 0 1em 0 !important; text-align: justify !important; }
          h1, h2, h3, h4 { line-height: 1.3 !important; margin: 1em 0 0.5em 0 !important; }
          img, svg, video { max-width: 100% !important; height: auto !important; }
          a { color: #8AB4F8 !important; }
          table { width: 100% !important; }
        </style>
    """.trimIndent()
    return if (html.contains("<head", ignoreCase = true)) {
        html.replaceFirst("<head", "<head>$css", ignoreCase = true)
    } else {
        "<html><head>$css</head><body>$html</body></html>"
    }
}
