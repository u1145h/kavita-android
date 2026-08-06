package com.u1145h.kavitaandroid.feature.reader

import android.os.SystemClock
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.u1145h.kavitaandroid.domain.model.BookFormat
import com.u1145h.kavitaandroid.ui.components.LoadingIndicator

/**
 * Full-screen reader. Routes to the native PDF, comic or EPUB reader and
 * persists reading progress (plus reading time) as the user flips pages.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    bookId: Long,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: ReaderViewModel = hiltViewModel()
    val book by viewModel.book.collectAsStateWithLifecycle()
    val b = book

    BackHandler { onBack() }

    if (b == null) {
        LoadingIndicator()
        return
    }

    LaunchedEffect(b.id) { viewModel.markOpened() }

    val startedAt = remember { SystemClock.elapsedRealtime() }
    DisposableEffect(b.id) {
        onDispose {
            viewModel.addReadingTime(SystemClock.elapsedRealtime() - startedAt)
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        contentWindowInsets = WindowInsets(0),
        topBar = {
            TopAppBar(
                title = { Text(b.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        val contentModifier = Modifier.padding(padding)
        when (b.format) {
            BookFormat.PDF -> PdfReader(
                filePath = b.filePath,
                initialPage = b.currentPage,
                onProgress = { page, percent -> viewModel.reportProgress(page, percent) },
                modifier = contentModifier,
            )

            BookFormat.CBZ, BookFormat.CBR -> ComicReader(
                filePath = b.filePath,
                initialPage = b.currentPage,
                onProgress = { page, percent -> viewModel.reportProgress(page, percent) },
                modifier = contentModifier,
            )

            BookFormat.EPUB -> EpubReader(
                filePath = b.filePath,
                initialHref = b.bookScrollId,
                onProgress = { page, percent, href ->
                    viewModel.reportProgress(page, percent, href)
                },
                modifier = contentModifier,
            )
        }
    }
}
