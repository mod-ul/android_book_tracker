package com.example.android_book_tracker.data.repository

import com.example.android_book_tracker.data.local.dao.BookDao
import com.example.android_book_tracker.domain.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    val books: Flow<List<Book>> = bookDao.getAllBooks()

    fun addBook(book: Book) {
        bookDao.addBook(book)
    }
}