package com.adolfochavez.aiformovies.data

import com.adolfochavez.aiformovies.BuildConfig
import com.adolfochavez.aiformovies.domain.Movie

class TmdbMovieRepository(
    private val service: TmdbService,
) : MovieRepository {
    override suspend fun popularMovies(): List<Movie> {
        check(BuildConfig.TMDB_API_KEY.isNotBlank()) {
            "TMDB_API_KEY is missing. Add it to secrets.properties."
        }
        return service.popularMovies(BuildConfig.TMDB_API_KEY)
            .results
            .map { it.toDomain() }
    }
}
