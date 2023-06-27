package com.devmasterteam.tasks.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.ActivityLoginBinding
import com.devmasterteam.tasks.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Variáveis da classe
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // Layout
        setContentView(binding.root)
        login()

        viewModel.verifyLoggedUser()

        observe()
    }

     fun login(){
        binding.buttonLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            viewModel.doLogin(email, password)
        }
    }

    private fun observe() {
        viewModel.login.observe(this, Observer {
            if (it.status()){
                startActivity()
            }else{
                Toast.makeText(applicationContext,it.message(),Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loggedUser.observe(this, Observer {
           if (it){
               startActivity()
           }

        })



    }

    fun startActivity(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}