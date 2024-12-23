package com.example.android_book_tracker.ui.screen.addbook

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_book_tracker.domain.model.Book
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.android_book_tracker.ui.screen.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun AddBookScreen(navController: NavHostController, viewModel: HomeViewModel = HomeViewModel()) {
    val fieldDrab = Color(0xFF6A552C)
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fieldDrab)
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val bookName = remember { mutableStateOf("") }
                val bookAuthor = remember { mutableStateOf("") }
                val bookPages = remember { mutableStateOf("") }
                val bookGenre = remember { mutableStateOf("") }
                val bookNotes = remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
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
                }

                Button(
                    onClick = {
                        if (bookName.value.isNotBlank() && bookAuthor.value.isNotBlank() && bookPages.value.toIntOrNull() != null) {

                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.repository.addBook(
                                    Book(
                                        name = bookName.value,
                                        author = bookAuthor.value,
                                        genre = bookGenre.value,
                                        note = bookNotes.value,
                                        startedAt = Date(),
                                        finishedAt = Date(),
                                        pages = bookPages.value.toInt()
                                    )
                                )
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
                    Text(text = "Add Book", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLabel(label: String, text: String, onTextChange: (String) -> Unit) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge, color = Color.White)

        TextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text(label) },
            textStyle = TextStyle(color = Color(0xFF352B16)),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray
            ),
            shape = RoundedCornerShape(30.dp)
        )
    }
}