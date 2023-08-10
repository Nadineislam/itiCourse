package com.example.iticourse.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance():Retrofit{
        val interceptor=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient=OkHttpClient.Builder().addInterceptor(interceptor).build()
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://reqres.in").client(okHttpClient).build()
    }
}