package com.teerap.codelab.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.teerap.codelab.mvvmsampleapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context : Context) : Interceptor {

    private val applicationContext = context.applicationContext


    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isIntetnetAvailable())
            throw NoInternetException("Internet Connection is offline")
        return chain.proceed(chain.request())
    }
    
    private fun isIntetnetAvailable() : Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}