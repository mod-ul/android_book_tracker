package com.example.android_book_tracker

import android.app.Application
import com.example.android_book_tracker.data.local.database.BookDatabase
import com.example.android_book_tracker.data.repository.BookRepository
import com.example.android_book_tracker.domain.model.Book
import java.util.Date

class MyApp : Application() {
    private val database by lazy {
        BookDatabase.getDatabase(this)
    }

    val repository by lazy {
        BookRepository(database.bookDao())
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val date = Date()

        repository.addBook(Book(
            name = "Book 1",
            author = "Author1",
            genre = "detective",
            note = "My first book !!!!!",
            startedAt = date,
            finishedAt = date
        ))
        repository.addBook(Book(
            name = "Book 2",
            author = "Author1",
            genre = "detective",
            note = "www.book to read",
            startedAt = date,
            finishedAt = date
        ))
        repository.addBook(Book(
            name = "Book 3",
            author = "Author1",
            genre = "detective",
            note = "i guess ill read it later lol",
            startedAt = date,
            finishedAt = date
        ))
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}