package com.adolfochavez.aiformovies.data

import com.adolfochavez.aiformovies.domain.Movie
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-MX",
    ): PopularMoviesResponse
}

data class PopularMoviesResponse(
    val results: List<TmdbMovieDto>,
)

data class TmdbMovieDto(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
) {
    fun toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
    )
}
