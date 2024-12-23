package com.example.android_book_tracker.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_book_tracker.domain.model.Book
import androidx.compose.ui.graphics.Color
import com.example.android_book_tracker.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = HomeViewModel()){
    val books by viewModel.books.collectAsState(initial = emptyList())

    val fieldDrab = Color(0xFF6A552C)
    val bone = Color(0xFFDFD6C1)

    Scaffold(
        topBar = {},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddBook.route)
                },
                containerColor = bone
            ) {
                Text(text = "+", style = MaterialTheme.typography.bodyLarge, color = fieldDrab)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            color = fieldDrab
        ) {
            if (books.isEmpty()) {
                EmptyList()
            } else {
                BooksList(navController, books)
            }
        }
    }
}

@Composable
fun EmptyList() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.alpha(0.5f).align(Alignment.Center),
            text = "There are no books in your list :((((((("
        )
    }
}

@Composable
fun BooksList(navController: NavHostController, books: List<Book>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(books) { book ->
            BookListItem(navController, book)
        }
    }
}

@Composable
fun BookListItem(navController: NavHostController, book: Book) {
    val drabDarkBrown = Color(0xFF352B16)
    val ecru = Color(0xFFBFAC83)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate("edit_book/${book.id}")
            }
    ) {
        Box(
            modifier = Modifier
                .background(ecru)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.displaySmall,
                    color = drabDarkBrown
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "by ${book.author}",
                    style = MaterialTheme.typography.labelLarge,
                    color = drabDarkBrown
                )
                Spacer(modifier = Modifier.padding(8.dp))
                LinearProgressIndicator(
                    progress = book.currentPage / book.pages.toFloat(),
                    modifier = Modifier.fillMaxWidth(),
                    color = drabDarkBrown
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Progress: ${book.currentPage} / ${book.pages} pages",
                    color = drabDarkBrown
                )
            }
        }
    }
}