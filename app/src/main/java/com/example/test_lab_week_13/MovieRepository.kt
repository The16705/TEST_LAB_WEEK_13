package com.example.test_lab_week_13

import com.example.test_lab_week_13.api.MovieService
import com.example.test_lab_week_13.database.MovieDao
import com.example.test_lab_week_13.database.MovieDatabase
import com.example.test_lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) {
    private val apiKey = "d2af5e1063beb03a3ef3d9c9c6dc77b0"

    fun fetchMovies(): Flow<List<Movie>> = flow {
        val movieDao: MovieDao = movieDatabase.movieDao()

        // Load movies from DB
        val savedMovies = movieDao.getMovies()

        if (savedMovies.isEmpty()) {
            // Fetch from API
            val movies = movieService.getPopularMovies(apiKey).results

            movieDao.addMovies(movies)

            emit(movies)
        } else {
            emit(savedMovies)
        }
    }.flowOn(Dispatchers.IO)
}
