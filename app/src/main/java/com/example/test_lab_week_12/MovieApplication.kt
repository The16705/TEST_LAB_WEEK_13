// File: app/src/main/java/com/example/test_lab_week_12/MovieApplication.kt
package com.example.test_lab_week_12

import android.app.Application
import com.example.test_lab_week_12.api.MovieService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApplication : Application() {
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val movieService = retrofit.create(MovieService::class.java)
        movieRepository = MovieRepository(movieService)
    }
}