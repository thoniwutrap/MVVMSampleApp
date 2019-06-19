package com.teerap.codelab.mvvmsampleapp.data.network.responses

import com.teerap.codelab.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
   val isSuccessful : Boolean?,
   val message : String?,
   val user : User?
)
