package com.example.jokegenerator.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add your API key and API host here
        val requestWithHeaders = originalRequest.newBuilder()
            .addHeader("x-rapidapi-key", "99ce56a1a0msha23dcce4e8f1a70p17f625jsn0e0d7f49bc37")
            .addHeader("x-rapidapi-host", "joke110.p.rapidapi.com")
            .build()

        return chain.proceed(requestWithHeaders)
    }
}
