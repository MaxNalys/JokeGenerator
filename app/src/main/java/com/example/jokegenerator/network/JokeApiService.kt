package com.example.jokegenerator.network

import com.example.jokegenerator.model.Joke
import retrofit2.Response
import retrofit2.http.GET

interface JokeApiService {
    @GET("/random_joke") // Define the correct endpoint from your API
    suspend fun getJoke(): Response<Joke>
}
