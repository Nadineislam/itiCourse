package com.example.iticourse.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iticourse.model.Post
import com.example.iticourse.repositories.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel:ViewModel() {
    private val postsRepository= PostsRepository()
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    fun getPostsByUser(userId: Int) {
        viewModelScope.launch {
            val response = postsRepository.getPostsByUser(userId)
            if (response.isSuccessful) {
                _posts.value = response.body()
            } else {
                // Handle error
            }
        }
    }
}