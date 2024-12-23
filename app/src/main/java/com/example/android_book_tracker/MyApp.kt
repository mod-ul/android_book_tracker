package com.example.android_book_tracker

import android.app.Application
import com.example.android_book_tracker.data.local.database.BookDatabase
import com.example.android_book_tracker.data.repository.BookRepository
import com.example.android_book_tracker.domain.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

//        CoroutineScope(Dispatchers.IO).launch {
//            repository.addBook(Book(
//                name = "Book 1",
//                author = "Author1",
//                genre = "detective",
//                note = "My first book !!!!!",
//                startedAt = date,
//                finishedAt = date,
//                pages = 100,
//                currentPage = 63
//            ))
//            repository.addBook(Book(
//                name = "Book 2",
//                author = "Author1",
//                genre = "detective",
//                note = "www.book to read",
//                startedAt = date,
//                finishedAt = date,
//                pages = 200
//            ))
//            repository.addBook(Book(
//                name = "Book 3",
//                author = "Author1",
//                genre = "detective",
//                note = "i guess ill read it later lol",
//                startedAt = date,
//                finishedAt = date,
//                pages = 300
//            ))
//        }

    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}