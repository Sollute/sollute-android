package com.sollute.estoque_certo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.product.NewProductFirstActivity
import com.sollute.estoque_certo.databinding.ActivityLoginBinding
import com.sollute.estoque_certo.models.login.Login
import com.sollute.estoque_certo.models.login.LoginResponse
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.AuthLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogar.setOnClickListener {
            tryLogin()
        }

    }

    private fun productScreen() {
        val productScreen = Intent(
            this,
            NewProductFirstActivity::class.java
        )
        startActivity(productScreen)
    }

    private fun tryLogin(){
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()
        val body = Login(email,senha)

        val request = Rest.getInstance()
            .create(AuthLogin::class.java)

        request.login(body).enqueue(
            object: Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Toast.makeText(
                        baseContext,
                        response.body()?.token,
                        Toast.LENGTH_LONG
                    ).show()
                    response.body()?.token
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    binding.txtErrorMessage.text = t.message
                }

            }
        )
    }
}