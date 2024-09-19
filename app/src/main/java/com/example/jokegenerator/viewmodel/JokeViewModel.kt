package com.example.jokegenerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokegenerator.model.Joke
import com.example.jokegenerator.repository.JokeRepository
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> = _joke

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchJoke() {
        viewModelScope.launch {
            val response = jokeRepository.getJoke()
            if (response.isSuccessful) {
                _joke.value = response.body()
            } else {
                _error.value = "Failed to load joke"
            }
        }
    }
}
