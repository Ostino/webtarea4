package com.example.tarea4.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea4.R
import com.example.tarea4.databinding.ActivityPostListBinding
import com.example.tarea4.ui.adapters.PostListAdapter
import com.example.tarea4.ui.viewmodels.PostListViewModel

class PostListActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostListBinding
    private val viewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupViewModelObservers()
        viewModel.getPostList()
    }

    private fun setupRecyclerView() {
        val adapter = PostListAdapter { post ->
            // Crear un Intent para abrir el MainActivity y pasar el ID del post
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("POST_ID", post.id.toInt()) // Pasar el ID del post
            }
            startActivity(intent)
        }
        binding.rvPostList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PostListActivity)
        }
    }


    private fun setupViewModelObservers() {
        viewModel.postList.observe(this) {
            val adapter = binding.rvPostList.adapter as PostListAdapter
            adapter.updateData(it)
        }
    }
}