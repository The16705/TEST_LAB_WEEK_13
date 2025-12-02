package com.example.test_lab_week_13

import android.util.Log
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

    // Fetch from DB first, fallback to API
    fun fetchMovies(): Flow<List<Movie>> = flow {
        val movieDao: MovieDao = movieDatabase.movieDao()

        val savedMovies = movieDao.getMovies()

        if (savedMovies.isEmpty()) {
            val moviesFromApi = movieService.getPopularMovies(apiKey).results

            movieDao.addMovies(moviesFromApi)

            emit(moviesFromApi)
        } else {
            emit(savedMovies)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun fetchMoviesFromNetwork() {
        val movieDao: MovieDao = movieDatabase.movieDao()

        try {
            val response = movieService.getPopularMovies(apiKey)
            val moviesFetched = response.results

            movieDao.addMovies(moviesFetched)

        } catch (e: Exception) {
            Log.e("MovieRepository", "Network fetch failed: ${e.message}")
        }
    }
}
