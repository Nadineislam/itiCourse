package com.example.iticourse.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    var id:Int,
    @SerializedName("postId")
                   var postId:Int,
    @SerializedName("email")
                   var email:String,
    @SerializedName("name")
    var name:String,
    @SerializedName("body")
    var body:String)
