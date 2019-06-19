package com.teerap.codelab.mvvmsampleapp.ui.auth

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
import com.teerap.codelab.mvvmsampleapp.data.db.entities.User
import com.teerap.codelab.mvvmsampleapp.databinding.ActivityLoginBinding
import com.teerap.codelab.mvvmsampleapp.utils.hide
import com.teerap.codelab.mvvmsampleapp.utils.show
import com.teerap.codelab.mvvmsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("${user.name} is Login in")
    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }


}
