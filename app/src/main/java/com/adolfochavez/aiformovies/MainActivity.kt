package com.adolfochavez.aiformovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.adolfochavez.aiformovies.ui.MovieApp
import com.adolfochavez.aiformovies.ui.theme.AIForMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val appContainer = (application as AiForMoviesApplication).appContainer
        setContent {
            AIForMoviesTheme {
                MovieApp(movieRepository = appContainer.movieRepository)
            }
        }
    }
}
