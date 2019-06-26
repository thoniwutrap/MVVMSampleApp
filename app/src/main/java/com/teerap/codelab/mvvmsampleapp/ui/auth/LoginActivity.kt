package com.teerap.codelab.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.teerap.codelab.mvvmsampleapp.R
import com.teerap.codelab.mvvmsampleapp.data.db.AppDatabase
import com.teerap.codelab.mvvmsampleapp.data.db.entities.User
import com.teerap.codelab.mvvmsampleapp.data.network.MyApi
import com.teerap.codelab.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.teerap.codelab.mvvmsampleapp.data.repositories.UserRepository
import com.teerap.codelab.mvvmsampleapp.databinding.ActivityLoginBinding
import com.teerap.codelab.mvvmsampleapp.ui.home.HomeActivity
import com.teerap.codelab.mvvmsampleapp.utils.hide
import com.teerap.codelab.mvvmsampleapp.utils.show
import com.teerap.codelab.mvvmsampleapp.utils.snackbar
import com.teerap.codelab.mvvmsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api,db)
        val factory =  AuthViewModelFactory(repository)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if(user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })


    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Login in")
    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        root_layout.snackbar(msg)
    }


}
