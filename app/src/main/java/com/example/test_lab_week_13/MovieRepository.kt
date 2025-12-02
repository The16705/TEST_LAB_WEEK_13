// File: app/src/main/java/com/example/test_lab_week_12/MovieRepository.kt
package com.example.test_lab_week_13

import com.example.test_lab_week_13.api.MovieService
import com.example.test_lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "d2af5e1063beb03a3ef3d9c9c6dc77b0"

    fun fetchMovies(): kotlinx.coroutines.flow.Flow<List<Movie>> = flow {
        val response = movieService.getPopularMovies(apiKey)
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}