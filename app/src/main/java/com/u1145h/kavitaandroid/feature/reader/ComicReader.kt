package com.u1145h.kavitaandroid.feature.reader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import java.io.File
import kotlin.math.roundToInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Native CBZ/CBR comic reader: a swipeable pager of page images. */
@Composable
fun ComicReader(
    filePath: String,
    initialPage: Int,
    onProgress: (Int, Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val archive = remember(filePath) { ComicExtractor.extract(context, filePath) }
    DisposableEffect(archive) {
        onDispose { archive.close() }
    }

    if (archive.pageCount == 0) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                "No readable pages found in this comic",
                style = MaterialTheme.typography.bodyLarge,
            )
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
        initialPage = initialPage.coerceIn(0, archive.pageCount - 1),
    ) { archive.pageCount }

    LaunchedEffect(pagerState.currentPage) {
        val page = pagerState.currentPage
        val percent = ((page + 1).toFloat() / archive.pageCount * 100f).coerceIn(0f, 100f)
        onProgress(page, percent)
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        key = { it },
    ) { index ->
        ComicPageView(
            file = archive.files[index],
            index = index,
            targetWidth = targetWidth,
            targetHeight = targetHeight,
            cache = cache,
        )
    }
}

@Composable
private fun ComicPageView(
    file: File,
    index: Int,
    targetWidth: Int,
    targetHeight: Int,
    cache: PageBitmapCache,
) {
    var bitmap by remember { mutableStateOf(cache[index]) }
    LaunchedEffect(index, targetWidth, targetHeight) {
        if (bitmap == null) {
            bitmap = withContext(Dispatchers.Default) {
                decodeSampled(file, targetWidth, targetHeight).also { cache[index] = it }
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

private fun decodeSampled(file: File, reqWidth: Int, reqHeight: Int): Bitmap {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    BitmapFactory.decodeFile(file.absolutePath, bounds)
    var sample = 1
    while (bounds.outWidth / (sample * 2) >= reqWidth ||
        bounds.outHeight / (sample * 2) >= reqHeight
    ) {
        sample *= 2
    }
    val options = BitmapFactory.Options().apply { inSampleSize = sample }
    return BitmapFactory.decodeFile(file.absolutePath, options)
        ?: Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
}
