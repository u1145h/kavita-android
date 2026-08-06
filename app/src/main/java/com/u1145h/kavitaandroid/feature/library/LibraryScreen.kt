package com.u1145h.kavitaandroid.feature.library

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.u1145h.kavitaandroid.ui.components.BookCard
import com.u1145h.kavitaandroid.ui.components.EmptyState
import com.u1145h.kavitaandroid.domain.model.Book

/** Offline library: every book downloaded from the Kavita server. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onOpenBook: (Long) -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: LibraryViewModel = hiltViewModel()
    val books by viewModel.books.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        contentWindowInsets = WindowInsets(0),
        topBar = {
            TopAppBar(
                title = { Text("Library") },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(
                            imageVector = Icons.Outlined.LibraryBooks,
                            contentDescription = "Settings",
                        )
                    }
                },
            )
        },
    ) { padding ->
        if (books.isEmpty()) {
            EmptyState(
                icon = Icons.Outlined.LibraryBooks,
                title = "No books yet",
                subtitle = "Download books from the Kavita web UI to read them offline.",
                action = {
                    Button(onClick = onOpenSettings) {
                        Text("Open Kavita")
                    }
                },
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 110.dp),
                contentPadding = padding,
                modifier = Modifier,
            ) {
                items(items = books, key = { it.id }) { book: Book ->
                    BookCard(
                        book = book,
                        onClick = { onOpenBook(book.id) },
                    )
                }
            }
        }
    }
}
