package com.example.tarea4.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tarea4.ui.viewmodels.MainViewModel
import com.example.tarea4.R
import com.example.tarea4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupEventListeners()
        setupViewModelObservers()

        // Obtener el ID del post pasado por el Intent
        val postId = intent.getIntExtra("POST_ID", -1)

        // Si se recibió un ID válido, cargar el post
        if (postId != -1) {
            viewModel.getPostById(postId)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.title.observe(this) {
            binding.lblTitle.text = it
        }
        viewModel.body.observe(this) {
            binding.lblBody.text = it
        }
    }

    private fun setupEventListeners() {
        binding.btnApiCall.setOnClickListener {
            viewModel.getPostById(1) // Esto seguirá funcionando para la llamada manual
        }
    }
}