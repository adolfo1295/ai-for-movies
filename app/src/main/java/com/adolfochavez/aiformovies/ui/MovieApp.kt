package com.adolfochavez.aiformovies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.adolfochavez.aiformovies.data.MovieRepository
import com.adolfochavez.aiformovies.domain.Movie

@Composable
fun MovieApp(movieRepository: MovieRepository) {
    val viewModel: MoviesViewModel = viewModel(
        factory = MoviesViewModel.factory(movieRepository),
    )
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MovieHomeScreen(
        state = state,
        onRetry = viewModel::loadPopularMovie,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieHomeScreen(
    state: MoviesUiState,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("AI for Movies") }) },
    ) { contentPadding ->
        when (state) {
            MoviesUiState.Loading -> LoadingContent(contentPadding)
            is MoviesUiState.Content -> MovieContent(contentPadding, state.movie)
            is MoviesUiState.Error -> ErrorContent(contentPadding, state.message, onRetry)
        }
    }
}

@Composable
private fun LoadingContent(contentPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MovieContent(contentPadding: PaddingValues, movie: Movie?) {
    if (movie == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("No hay películas populares disponibles.")
        }
        return
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding)
            .padding(24.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = movie.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
                contentDescription = "Póster de ${movie.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 240.dp, height = 360.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Calificación: ${"%.1f".format(movie.voteAverage)}",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = movie.overview,
                modifier = Modifier.padding(top = 12.dp),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun ErrorContent(
    contentPadding: PaddingValues,
    message: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("No pudimos cargar la película.")
        Text(
            text = message,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodySmall,
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text("Reintentar")
        }
    }
}
