package com.teerap.codelab.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.teerap.codelab.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user : User)
    fun onFailure(msg : String)
}