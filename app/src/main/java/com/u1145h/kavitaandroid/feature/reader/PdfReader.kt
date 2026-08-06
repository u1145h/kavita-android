package com.u1145h.kavitaandroid.feature.reader

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Native PDF reader powered by [android.graphics.pdf.PdfRenderer]. */
@Composable
fun PdfReader(
    filePath: String,
    initialPage: Int,
    onProgress: (Int, Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pdf = remember(filePath) { PdfPages(filePath) }
    DisposableEffect(pdf) {
        onDispose { pdf.close() }
    }

    if (pdf.pageCount == 0) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("This PDF has no pages", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val densityScale = LocalDensity.current.density
    val targetWidth = (configuration.screenWidthDp * densityScale).roundToInt()
    val targetHeight = (configuration.screenHeightDp * densityScale).roundToInt()
    val cache = remember { PageBitmapCache() }

    val pagerState = rememberPagerState(
        initialPage = initialPage.coerceIn(0, pdf.pageCount - 1),
    ) { pdf.pageCount }

    LaunchedEffect(pagerState.currentPage) {
        val page = pagerState.currentPage
        val percent = ((page + 1).toFloat() / pdf.pageCount * 100f).coerceIn(0f, 100f)
        onProgress(page, percent)
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        key = { it },
    ) { index ->
        PdfPageView(
            pdf = pdf,
            index = index,
            targetWidth = targetWidth,
            targetHeight = targetHeight,
            cache = cache,
        )
    }
}

@Composable
private fun PdfPageView(
    pdf: PdfPages,
    index: Int,
    targetWidth: Int,
    targetHeight: Int,
    cache: PageBitmapCache,
) {
    var bitmap by remember { mutableStateOf(cache[index]) }
    LaunchedEffect(index, targetWidth, targetHeight) {
        if (bitmap == null) {
            bitmap = withContext(Dispatchers.Default) {
                pdf.render(index, targetWidth, targetHeight).also { cache[index] = it }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        val bmp = bitmap
        if (bmp != null) {
            Image(
                bitmap = bmp.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
            )
        } else {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}
