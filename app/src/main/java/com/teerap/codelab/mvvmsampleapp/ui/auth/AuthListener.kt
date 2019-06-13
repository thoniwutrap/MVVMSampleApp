package com.teerap.codelab.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse : LiveData<String>)
    fun onFailure(msg : String)
}