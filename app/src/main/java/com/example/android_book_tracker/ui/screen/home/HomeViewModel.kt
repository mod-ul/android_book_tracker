package com.example.android_book_tracker.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.android_book_tracker.MyApp
import com.example.android_book_tracker.domain.model.Book
import kotlinx.coroutines.flow.Flow

class HomeViewModel: ViewModel() {
    val repository = MyApp.instance.repository
    val books: Flow<List<Book>> = repository.books
}