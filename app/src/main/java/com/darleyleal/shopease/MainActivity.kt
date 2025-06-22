package com.darleyleal.shopease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.darleyleal.shopease.presentation.screens.main.MainScreen
import com.darleyleal.shopease.presentation.screens.main.MainViewModel
import com.darleyleal.shopease.ui.theme.ShopEaseTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopEaseTheme {
                MainScreen(
                    viewModel = mainViewModel
                )
            }
        }
    }
}