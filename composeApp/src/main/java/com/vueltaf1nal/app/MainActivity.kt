package com.vueltaf1nal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vueltaf1nal.app.data.remote.ResultsService
import com.vueltaf1nal.app.data.remote.TopSpeedsService
import com.vueltaf1nal.app.data.repository.ResultsRepositoryImpl
import com.vueltaf1nal.app.data.repository.TopSpeedsRepositoryImpl
import com.vueltaf1nal.app.ui.screens.*
import com.vueltaf1nal.app.ui.theme.*
import com.vueltaf1nal.app.ui.viewmodel.*

class MainActivity : ComponentActivity() {

    // Simple manual DI for the ViewModel
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val resultsRepository = ResultsRepositoryImpl(ResultsService())
                val topSpeedsRepository = TopSpeedsRepositoryImpl(TopSpeedsService())
                return MainViewModel(resultsRepository, topSpeedsRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VueltaF1nalTheme {
                val currentScreen by viewModel.currentScreen.collectAsState()
                val results by viewModel.results.collectAsState()
                val topSpeeds by viewModel.topSpeeds.collectAsState()
                val isLoading by viewModel.isLoading.collectAsState()
                val isTopSpeedsLoading by viewModel.isTopSpeedsLoading.collectAsState()
                val error by viewModel.error.collectAsState()

                Surface(modifier = Modifier.fillMaxSize(), color = DeepNavy) {
                    if (isLoading) {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = RacingRed)
                        }
                    } else if (error != null) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(error!!, color = Color.White)
                        }
                    } else {
                        when (currentScreen) {
                            is Screen.List ->
                                    ResultListScreen(
                                            results = results,
                                            onSeeTopSpeeds = {
                                                viewModel.navigateTo(Screen.TopSpeeds)
                                            }
                                    )
                            is Screen.TopSpeeds ->
                                    TopSpeedsScreen(
                                            topSpeeds = topSpeeds,
                                            isLoading = isTopSpeedsLoading,
                                            onBack = { viewModel.navigateTo(Screen.List) }
                                    )
                        }
                    }
                }
            }
        }
    }
}
