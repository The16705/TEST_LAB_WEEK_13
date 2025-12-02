package com.example.test_lab_week_13

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val movieRepository = (applicationContext as MovieApplication).movieRepository

        return try {
            withContext(Dispatchers.IO) {
                movieRepository.fetchMoviesFromNetwork()
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
