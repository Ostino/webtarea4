package com.example.tarea4.api

import com.example.tarea4.models.Post
import com.example.tarea4.models.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderService {
    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @GET("/posts")
    fun getPostList(): Call<Posts>
}