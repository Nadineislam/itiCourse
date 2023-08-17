package com.example.iticourse.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iticourse.repositories.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class UserViewModel:ViewModel() {
    private val userRepository = UserRepository()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val response = userRepository.login(userName, password)
            if (response.isSuccessful) {
                _loginSuccess.value = true
            } else {
                val json = JSONObject(response.errorBody()?.string())
                val errorMessage = json.getString("message")

            }
        }
    }
}