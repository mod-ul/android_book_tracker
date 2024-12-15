package com.example.android_book_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.android_book_tracker.ui.navigation.NavSetup
import com.example.android_book_tracker.ui.screen.splash.SplashViewModel
import com.example.android_book_tracker.ui.theme.Android_book_trackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = SplashViewModel()
        installSplashScreen().setKeepOnScreenCondition { viewModel.isLoading.value }

        enableEdgeToEdge()

        setContent {
            NavSetup()
        }
    }
}