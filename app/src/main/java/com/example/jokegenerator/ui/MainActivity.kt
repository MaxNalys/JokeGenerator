package com.example.jokegenerator.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jokegenerator.databinding.ActivityMainBinding
import com.example.jokegenerator.viewmodel.JokeViewModel
import com.example.jokegenerator.viewmodel.JokeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Provide an instance of JokeRepository
    private val jokeViewModel: JokeViewModel by viewModels {
        JokeViewModelFactory() // No need to pass the repository here
    }
    // ViewModel with the factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe the joke LiveData
        jokeViewModel.joke.observe(this) { joke ->
            binding.generatedJoke.text = "${joke.setup}\n${joke.punchline}"
        }

        // Observe the error LiveData
        jokeViewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        // Set click listener for generate button
        binding.generateBtn.setOnClickListener {
            jokeViewModel.fetchJoke()
        }

        // Set click listener for share button
        binding.shareBtn.setOnClickListener {
            val jokeText = binding.generatedJoke.text.toString()
            if (jokeText.isNotEmpty()) {
                shareJoke(jokeText)
            } else {
                Toast.makeText(this, "No joke to share!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun shareJoke(jokeText: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, jokeText)
            type = "text/plain"
        }
        val chooser = Intent.createChooser(shareIntent, "Share joke via")
        startActivity(chooser)
    }
}
