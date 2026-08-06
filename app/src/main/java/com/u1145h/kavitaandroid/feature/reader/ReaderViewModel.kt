package com.u1145h.kavitaandroid.feature.reader

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1145h.kavitaandroid.data.repository.BookRepository
import com.u1145h.kavitaandroid.domain.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ReaderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val bookId: Long = checkNotNull(savedStateHandle["bookId"])

    val book: StateFlow<Book?> = bookRepository
        .observeBook(bookId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    /** Persists reading progress; local changes are pushed to the server by [SyncWorker]. */
    fun reportProgress(page: Int, percent: Float, scrollId: String? = null) {
        viewModelScope.launch {
            book.value?.let { current ->
                if (scrollId != null && scrollId != current.bookScrollId) {
                    bookRepository.saveBook(current.copy(bookScrollId = scrollId))
                }
            }
            bookRepository.updateProgress(bookId, page, percent)
        }
    }

    fun addReadingTime(ms: Long) {
        viewModelScope.launch { bookRepository.addReadingTime(bookId, ms) }
    }

    fun markOpened() {
        viewModelScope.launch { bookRepository.markOpened(bookId) }
    }
}
