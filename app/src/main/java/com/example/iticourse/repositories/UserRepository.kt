package com.example.iticourse.repositories

import com.example.iticourse.api.RetrofitClient
import com.example.iticourse.model.LoginBodyRequest
import com.example.iticourse.model.UserResponse
import retrofit2.Response

class UserRepository {
    private val retrofit = RetrofitClient.getInstance("https://dummyjson.com")

    suspend fun login(userName: String, password: String): Response<UserResponse> {
        val body = LoginBodyRequest(userName, password)
        return retrofit.login(body)
    }
}