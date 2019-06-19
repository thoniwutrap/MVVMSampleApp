package com.teerap.codelab.mvvmsampleapp.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.teerap.codelab.mvvmsampleapp.data.repositories.UserRepository
import com.teerap.codelab.mvvmsampleapp.utils.Coroutines

class AuthViewModel : ViewModel() {

    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main{
            val response  = UserRepository().userLogin(email!!,password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error code ${response.code()}")
            }
        }

    }



}