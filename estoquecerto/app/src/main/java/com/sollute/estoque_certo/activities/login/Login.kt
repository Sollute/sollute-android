package com.sollute.estoque_certo.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.product.NewProductFirstActivity
import com.sollute.estoque_certo.activities.register.RegisterActivity
import com.sollute.estoque_certo.databinding.ActivityLoginBinding
import com.sollute.estoque_certo.models.login.Login
import com.sollute.estoque_certo.models.login.LoginResponse
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.auth.AuthLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { tryLogin() }
        binding.btnGoRegister.setOnClickListener { goRegister() }
    }

    private fun goRegister() { startActivity(Intent(this, RegisterActivity::class.java)) }

    private fun productScreen() {
        val productScreen = Intent(
            this,
            NewProductFirstActivity::class.java
        )
        startActivity(productScreen)
    }

    private fun tryLogin() {
        val body = Login(
            binding.etEmail.text.toString(),
            binding.etSenha.text.toString()
        )

        val request = Rest.getInstance().create(AuthLogin::class.java)

        request.login(body).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Toast.makeText(
                    baseContext,
                    response.body()?.token,
                    Toast.LENGTH_LONG
                ).show()
                productScreen()
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

}
