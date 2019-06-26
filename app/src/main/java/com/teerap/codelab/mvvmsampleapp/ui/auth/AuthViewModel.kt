package com.teerap.codelab.mvvmsampleapp.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.teerap.codelab.mvvmsampleapp.data.repositories.UserRepository
import com.teerap.codelab.mvvmsampleapp.utils.ApiException
import com.teerap.codelab.mvvmsampleapp.utils.Coroutines
import com.teerap.codelab.mvvmsampleapp.utils.NoInternetException
import kotlinx.coroutines.yield

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main{
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }catch (ex : ApiException){
                authListener?.onFailure(ex.message!!)
            }catch (ex : NoInternetException){
                authListener?.onFailure(ex.message!!)
            }

        }

    }



}