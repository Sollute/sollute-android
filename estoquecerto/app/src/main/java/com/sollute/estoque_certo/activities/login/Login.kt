package com.sollute.estoque_certo.activities.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
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
    private val request: AuthLogin = Rest.getInstance().create(AuthLogin::class.java)
    private var isOnline: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkStatus()

        binding.btnLogin.setOnClickListener { tryLogin(isOnline) }
        binding.btnGoRegister.setOnClickListener { goRegister() }
    }

    private fun goRegister() = startActivity(Intent(this, RegisterActivity::class.java))

    private fun productScreen() {
        val productScreen = Intent(
            this,
            ProductActivity::class.java
        )
        startActivity(productScreen)
    }

    private fun checkStatus() {
        request.check().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                getPreferences(MODE_PRIVATE).apply { this.edit().putBoolean("isOnline", true).apply() }
                isOnline = true
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                getPreferences(MODE_PRIVATE).apply { this.edit().putBoolean("isOnline", false).apply() }
                isOnline = false
            }
        })
    }

    private fun tryLogin(isOnline: Boolean) {

        val body = Login(
            login = binding.etLogin.text.toString(),
            senha = binding.etSenha.text.toString()
        )

        if (isOnline) {
            request.login(body).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    when {
                        response.code() == 200 -> {
                            getPreferences(MODE_PRIVATE).apply {
                                this.edit().putInt("idEmpresa", response.body()!!.idEmpresa).apply()
                            }
                            productScreen()
                        }
                        (response.code() == 401) -> {
                            Toast.makeText(
                                baseContext,
                                "Credenciais incorretas",
                                Toast.LENGTH_LONG
                            ).show().also {
                                binding.etLogin.error
                                binding.etSenha.error
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponse>,
                    t: Throwable
                ) {
                    Toast.makeText(
                        baseContext,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        } else {
            if (body.login.equals("sollute@gmail.com") && (body.senha.equals("sollute123"))) {
                productScreen()
            } else {
                Toast.makeText(
                    baseContext,
                    "Credenciais incorretas",
                    Toast.LENGTH_LONG
                ).show().also {
                    binding.etLogin.error
                    binding.etSenha.error
                }
            }
        }

    }

}
