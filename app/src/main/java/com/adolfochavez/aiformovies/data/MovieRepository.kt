package com.adolfochavez.aiformovies.data

import com.adolfochavez.aiformovies.domain.Movie

interface MovieRepository {
    suspend fun popularMovies(): List<Movie>
}
