package com.teerap.codelab.mvvmsampleapp.data.repositories


import com.teerap.codelab.mvvmsampleapp.data.db.AppDatabase
import com.teerap.codelab.mvvmsampleapp.data.db.entities.User
import com.teerap.codelab.mvvmsampleapp.data.network.MyApi
import com.teerap.codelab.mvvmsampleapp.data.network.SafeApiRequest
import com.teerap.codelab.mvvmsampleapp.data.network.responses.AuthResponse

class UserRepository(
    private val api : MyApi,
    private val db : AppDatabase
): SafeApiRequest() {


    suspend fun userLogin(email : String, password : String) : AuthResponse{
        return apiRequest{api.userLogin(email,password)}
    }

    suspend fun saveUser(user : User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}