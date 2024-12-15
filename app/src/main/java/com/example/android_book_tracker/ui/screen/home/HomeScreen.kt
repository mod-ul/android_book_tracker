package com.example.android_book_tracker.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = HomeViewModel()){
    val books by viewModel.books.collectAsState(initial = emptyList())

    Scaffold(topBar = {}, floatingActionButton = {}) {
        Surface {
            if (books.isEmpty()) {
                EmptyList()
            } else {

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
            modifier = Modifier.alpha(0.5f).align(Alignment.Center),
            text = "There are no books in your list :("
        )
    }
}

@Composable
fun BooksList(books: List<Book>) {
    LazyColumn {
        items(books) {
            BookListItem(it)
        }
    }
}

@Composable
fun BookListItem(book: Book) {
    Card(
        //elevation = 9.dp,
        modifier = Modifier.fillMaxSize().padding(all = 8.dp)
    ) {
        Text(text = book.name)
    }
}