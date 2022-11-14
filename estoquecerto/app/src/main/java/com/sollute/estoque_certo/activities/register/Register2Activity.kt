package com.sollute.estoque_certo.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.login.Login
import com.sollute.estoque_certo.databinding.ActivityRegister2Binding

class Register2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.tvLogin.setOnClickListener { login() }
        binding.btnNextPageRegisterProduct.setOnClickListener { nextStep() }
    }

    private fun login() = startActivity(Intent(this, Login::class.java))

    private fun nextStep() {

        val companyEmail = intent!!.getStringExtra("companyEmail")!!
        val companyPass = intent!!.getStringExtra("companyPass")!!
        val companyPassConfirm = intent!!.getStringExtra("companyPassConfirm")!!
        val companyName = binding.etNomeFantasia.text.toString()
        val companyComplementName = binding.etRazaoSocial.text.toString()
        val companyCnpj = binding.etCNPJ.text.toString()
        val companyPhone = binding.etTelefoneCelular.text.toString()

        val nextScreen = Intent(
            this,
            Register3Activity::class.java
        ).apply {
            this.putExtra("companyEmail", companyEmail)
            this.putExtra("companyPass", companyPass)
            this.putExtra("companyPassConfirm", companyPassConfirm)
            this.putExtra("companyName", companyName)
            this.putExtra("companyComplementName", companyComplementName)
            this.putExtra("companyCnpj", companyCnpj)
            this.putExtra("companyPhone", companyPhone)
        }

        startActivity(nextScreen)
    }
}
