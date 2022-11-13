package com.sollute.estoque_certo.activities.login

import android.content.Intent
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
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val request = Rest.getInstance().create(AuthLogin::class.java)
    var isOnline: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkStatus().wait()

        binding.btnLogin.setOnClickListener { tryLogin(isOnline) }
        binding.btnGoRegister.setOnClickListener { goRegister() }
    }

    private fun goRegister() = startActivity(Intent(this, RegisterActivity::class.java))

    private fun productScreen(idEmpresa: Int) {
        val productScreen = Intent(
            this,
            ProductActivity::class.java
        )
        productScreen.putExtra("isOnline", true)
        productScreen.putExtra("idEmp", idEmpresa)
        startActivity(productScreen)
    }

    private fun checkStatus() =
        request.check().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                isOnline = true
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                isOnline = false
            }
        })

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
                            productScreen(response.body()!!.idEmpresa)
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
                productScreen(1)
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
