package com.example.jokegenerator.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://joke110.p.rapidapi.com/" // Replace with your base URL

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor()) // Add the interceptor here
        .build()

    val instance: JokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApiService::class.java)
    }
}
