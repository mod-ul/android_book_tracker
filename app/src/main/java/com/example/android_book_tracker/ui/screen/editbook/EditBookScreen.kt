package com.example.android_book_tracker.ui.screen.editbook

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_book_tracker.domain.model.Book
import androidx.compose.ui.graphics.Color
import com.example.android_book_tracker.ui.screen.addbook.TextFieldWithLabel
import com.example.android_book_tracker.ui.screen.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

@Composable
fun EditBookScreen(navController: NavHostController, bookId: Long?, viewModel: HomeViewModel = HomeViewModel()) {
    val fieldDrab = Color(0xFF6A552C)
    val snackbarHostState = remember { SnackbarHostState() }

    val bookName = remember { mutableStateOf("") }
    val bookAuthor = remember { mutableStateOf("") }
    val bookPages = remember { mutableStateOf("") }
    val bookGenre = remember { mutableStateOf("") }
    val bookNotes = remember { mutableStateOf("") }
    val startedAt = remember { mutableStateOf("") }
    val finishedAt = remember { mutableStateOf("") }
    val currentPage = remember { mutableStateOf("") }

    val bookState: MutableState<Book?> = remember { mutableStateOf(null) }

    LaunchedEffect(bookId) {
        bookId?.let {
            val book = withContext(Dispatchers.IO) {
                viewModel.repository.getBookById(it)
            }

            bookState.value = book

            bookName.value = book.name.toString()
            bookAuthor.value = book.author.toString()
            bookPages.value = book.pages.toString()
            bookGenre.value = book.genre.toString()
            bookNotes.value = book.note.toString()
            startedAt.value = book.startedAt.toString()
            finishedAt.value = book.finishedAt.toString()
            currentPage.value = book.currentPage.toString()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fieldDrab)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextFieldWithLabel("Book Title", bookName.value) {
                        bookName.value = it
                    }
                    TextFieldWithLabel("Author", bookAuthor.value) {
                        bookAuthor.value = it
                    }
                    TextFieldWithLabel("Total Pages", bookPages.value) {
                        bookPages.value = it
                    }
                    TextFieldWithLabel("Genre", bookGenre.value) {
                        bookGenre.value = it
                    }
                    TextFieldWithLabel("Notes", bookNotes.value) {
                        bookNotes.value = it
                    }
                    TextFieldWithLabel("Started At", startedAt.value) {
                        startedAt.value = it
                    }
                    TextFieldWithLabel("Finished At", finishedAt.value) {
                        finishedAt.value = it
                    }
                    TextFieldWithLabel("Current Page", currentPage.value) {
                        currentPage.value = it
                    }
                }

                Button(
                    onClick = {
                        if (bookName.value.isNotBlank() && bookAuthor.value.isNotBlank() && bookPages.value.toIntOrNull() != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                if (bookId == null) {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        snackbarHostState.showSnackbar(
                                            message = "bookId is null",
                                            actionLabel = "Sadge :("
                                        )
                                    }
                                } else {
                                    viewModel.repository.editBook(
                                        Book(
                                            id = bookId,
                                            name = bookName.value,
                                            author = bookAuthor.value,
                                            genre = bookGenre.value,
                                            note = bookNotes.value,
                                            startedAt = Date(),
                                            finishedAt = Date(),
                                            pages = bookPages.value.toInt(),
                                            currentPage = currentPage.value.toIntOrNull() ?: 0
                                        )
                                    )
                                }
                            }
                            navController.popBackStack()
                        } else {
                            CoroutineScope(Dispatchers.IO).launch {
                                snackbarHostState.showSnackbar(
                                    message = "Please enter valid details!",
                                    actionLabel = "Dismiss"
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF352B16),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Edit Book", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}