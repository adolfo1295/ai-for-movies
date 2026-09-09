package com.adolfochavez.aiformovies

import android.app.Application
import com.adolfochavez.aiformovies.data.MovieRepository
import com.adolfochavez.aiformovies.data.TmdbMovieRepository
import com.adolfochavez.aiformovies.data.TmdbService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AiForMoviesApplication : Application() {
    val appContainer: AppContainer by lazy { DefaultAppContainer() }
}

interface AppContainer {
    val movieRepository: MovieRepository
}

private class DefaultAppContainer : AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TmdbService::class.java)

    override val movieRepository: MovieRepository = TmdbMovieRepository(service)
}
