// File: app/src/main/java/com/example/test_lab_week_12/MovieViewModel.kt
package com.example.test_lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String> = _error

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            movieRepository.fetchMovies()
                .catch { exception ->
                    _error.value = "An exception occurred: ${exception.message}"
                }
                .collect { movies ->
                    _popularMovies.value = movies
                }
        }
    }
}