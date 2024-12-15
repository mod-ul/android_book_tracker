package com.example.android_book_tracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_book_tracker.ui.screen.addbook.AddBookScreen
import com.example.android_book_tracker.ui.screen.editbook.EditBookScreen
import com.example.android_book_tracker.ui.screen.home.HomeScreen

@Composable
fun NavSetup(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController)}
        composable(Screen.AddBook.route) { AddBookScreen(navController)}
        composable(Screen.EditBook.route) { EditBookScreen(navController)}
    }
}