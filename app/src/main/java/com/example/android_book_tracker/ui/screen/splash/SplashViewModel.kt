package com.example.android_book_tracker.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _mutableStateFlow = MutableStateFlow(true)
    val isLoading = _mutableStateFlow.asStateFlow()

    private suspend fun imitateLoading() {
        delay(2000)
        _mutableStateFlow.value = false
    }
    init {
        viewModelScope.launch {
            imitateLoading()
        }
    }
}