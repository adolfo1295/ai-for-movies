package com.adolfochavez.aiformovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.adolfochavez.aiformovies.data.MovieRepository
import com.adolfochavez.aiformovies.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface MoviesUiState {
    data object Loading : MoviesUiState
    data class Content(val movie: Movie?) : MoviesUiState
    data class Error(val message: String) : MoviesUiState
}

class MoviesViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        loadPopularMovie()
    }

    fun loadPopularMovie() {
        viewModelScope.launch {
            _uiState.update { MoviesUiState.Loading }
            runCatching { movieRepository.popularMovies().firstOrNull() }
                .onSuccess { movie -> _uiState.update { MoviesUiState.Content(movie) } }
                .onFailure { error ->
                    _uiState.update {
                        MoviesUiState.Error(error.message ?: "Ocurrió un error inesperado.")
                    }
                }
        }
    }

    companion object {
        fun factory(movieRepository: MovieRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    MoviesViewModel(movieRepository) as T
            }
    }
}
