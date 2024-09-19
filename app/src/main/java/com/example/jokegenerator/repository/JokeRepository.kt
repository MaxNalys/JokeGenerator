package com.example.jokegenerator.repository

import com.example.jokegenerator.model.Joke
import com.example.jokegenerator.network.JokeApiService
import retrofit2.Response

class JokeRepository(private val jokeApiService: JokeApiService) {
    suspend fun getJoke(): Response<Joke> {
        return jokeApiService.getJoke()
    }
}
