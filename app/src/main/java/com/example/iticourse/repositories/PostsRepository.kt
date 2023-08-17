package com.example.iticourse.repositories

import com.example.iticourse.api.RetrofitClient
import com.example.iticourse.model.Post
import retrofit2.Response

class PostsRepository {
    suspend fun getPostsByUser(userId: Int): Response<ArrayList<Post>> {
        return RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/").getPostsByUser(userId)
    }
}