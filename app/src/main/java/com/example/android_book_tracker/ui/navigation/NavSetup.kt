package com.example.android_book_tracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_book_tracker.ui.screen.addbook.AddBookScreen
import com.example.android_book_tracker.ui.screen.editbook.EditBookScreen
import com.example.android_book_tracker.ui.screen.home.HomeScreen

@Composable
fun NavSetup(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController)}
        composable(Screen.AddBook.route) { AddBookScreen(navController)}
        composable(
            "edit_book/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getLong("bookId") ?: -1L
            EditBookScreen(navController = navController, bookId = bookId)
        }
    }
}