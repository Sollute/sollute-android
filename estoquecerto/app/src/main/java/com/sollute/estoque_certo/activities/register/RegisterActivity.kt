package com.sollute.estoque_certo.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.login.Login
import com.sollute.estoque_certo.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.btnNextPageRegisterProduct.setOnClickListener { nextStep() }
        binding.tvLogin.setOnClickListener { login() }
    }

    private fun login() = startActivity(Intent(this, Login::class.java))

    private fun nextStep() {

        val companyEmail = binding.etEmail.text.toString()
        val companyPass = binding.etSenha.text.toString()
        val companyPassConfirm = binding.etConfirmeSenha.text.toString()

        if (companyPass != companyPassConfirm) {
            binding.etSenha.error = "As senhas não conferem"
            binding.etConfirmeSenha.error = "As senhas não conferem"
        } else {

            val nextScreen = Intent(
                this,
                Register2Activity::class.java
            ).apply {
                this.putExtra("companyEmail", companyEmail)
                this.putExtra("companyPass", companyPass)
                this.putExtra("companyPassConfirm", companyPassConfirm)
            }

            startActivity(nextScreen)
        }

    }
}
