package com.example.android_book_tracker.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object AddBook: Screen("addbook")
    object EditBook: Screen("edit_book/{bookId}")
}