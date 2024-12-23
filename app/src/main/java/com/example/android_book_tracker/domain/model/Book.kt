package com.example.android_book_tracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var author: String = "",
    var genre: String = "",
    var note: String = "",
    var startedAt: Date = Date(),
    var finishedAt: Date = Date(),
    var pages: Int = 1,
    var currentPage: Int = 1
)