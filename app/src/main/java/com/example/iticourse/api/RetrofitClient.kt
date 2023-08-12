package com.example.iticourse.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance():UserApi{
        val interceptor=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient=OkHttpClient.Builder().addInterceptor(interceptor).build()
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient).build().create(UserApi::class.java)
    }
}