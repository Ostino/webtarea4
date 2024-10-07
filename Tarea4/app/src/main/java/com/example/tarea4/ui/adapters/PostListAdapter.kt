package com.example.tarea4.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea4.R
import com.example.tarea4.databinding.PostItemLayoutBinding
import com.example.tarea4.models.Post
import com.example.tarea4.models.Posts

class PostListAdapter(private val onItemClick: (Post) -> Unit)
    : RecyclerView.Adapter<PostListAdapter.PostItemViewHolder>() {
    private var postList: Posts = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding = PostItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemViewHolder(binding.root, onItemClick)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun updateData(it: Posts) {
        postList = it
        notifyDataSetChanged()
    }

    class PostItemViewHolder(
        itemView: View,
        private val onItemClick: (Post) -> Unit // Recibir el callback en el ViewHolder
    ) : RecyclerView.ViewHolder(itemView) {
        private val lblPostItemTitle: TextView = itemView.findViewById(R.id.lblPostItemTitle)

        fun bind(post: Post) {
            lblPostItemTitle.text = post.title
            itemView.setOnClickListener {
                onItemClick(post) // Llamar al callback cuando se hace clic
            }
        }
    }
}