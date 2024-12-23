package com.example.android_book_tracker.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_book_tracker.data.local.dao.BookDao
import com.example.android_book_tracker.domain.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, BookDatabase::class.java, "book_database")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}