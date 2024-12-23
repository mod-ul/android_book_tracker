package com.example.android_book_tracker.data.repository

import com.example.android_book_tracker.data.local.dao.BookDao
import com.example.android_book_tracker.domain.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    val books: Flow<List<Book>> = bookDao.getAllBooks()

    fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun editBook(book: Book) {
        bookDao.editBook(book)
    }

    fun getBookById(id: Long) : Book {
        val book = bookDao.getBookById(id)
        return book
    }
}