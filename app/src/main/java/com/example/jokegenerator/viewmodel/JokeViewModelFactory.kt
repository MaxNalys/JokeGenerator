package com.example.jokegenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jokegenerator.network.RetrofitClient
import com.example.jokegenerator.repository.JokeRepository

class JokeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            val jokeRepository = JokeRepository(RetrofitClient.instance) // Use the Retrofit client here
            return JokeViewModel(jokeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
