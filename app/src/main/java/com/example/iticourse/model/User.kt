package com.example.iticourse.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id:Int,
    @SerializedName("first_name")
    var firstName :String,
    @SerializedName("last_name")
    var lastName:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("avatar")
    var avatar:String
    )
