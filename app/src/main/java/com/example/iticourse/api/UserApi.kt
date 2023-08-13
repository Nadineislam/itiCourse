package com.example.iticourse.api

import com.example.iticourse.model.*
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @GET("/api/users")
    suspend fun getUser(): Response<ResponseUsersList>

    @GET("posts")
    suspend fun getPosts(): Response<ArrayList<Post>>

    @GET("posts")
    suspend fun getPostsByUser(@Query("userId") userId: Int): Response<ArrayList<Post>>

    @GET("posts/{post_id}/comments")
    suspend fun getComments(@Path("post_id") postId: Int): Response<ArrayList<Comment>>

    @POST("/auth/login")
    suspend fun login(@Body body: LoginBodyRequest): Response<UserResponse>

}