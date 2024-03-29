package com.teerap.codelab.mvvmsampleapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teerap.codelab.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository = repository) as T
    }

}