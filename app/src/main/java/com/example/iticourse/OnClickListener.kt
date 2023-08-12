package com.example.iticourse

import com.example.iticourse.model.Post
import com.example.iticourse.model.User

interface OnClickListener {
    fun onClick(post: Post, position:Int)
}