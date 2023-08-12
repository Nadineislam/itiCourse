package com.example.iticourse.model

import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("body")
    var body: String,
    @SerializedName("title")
                var title: String,
    @SerializedName("id")
    var id:Int,
    @SerializedName("userId")
    var userId:Int)
