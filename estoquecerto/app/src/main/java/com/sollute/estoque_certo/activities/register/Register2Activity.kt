package com.sollute.estoque_certo.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityRegister2Binding

class Register2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister2Binding.inflate(layoutInflater)
        bundle = intent.extras!!
        setContentView(binding.root)

        binding.btnNextPageRegisterProduct.setOnClickListener { nextStep() }
    }

    private fun nextStep() {

        val companyEmail = bundle!!.getString("companyEmail")!!
        val companyPass = bundle!!.getString("companyPass")!!
        val companyPassConfirm = bundle!!.getString("companyPassConfirm")!!
        val companyName = binding.etNomeFantasia.text.toString()
        val companyComplementName = binding.etRazaoSocial.text.toString()
        val companyCnpj = binding.etCNPJ.text.toString()
        val companyPhone = binding.etTelefoneCelular.text.toString()

        val nextScreen = Intent(
            this,
            Register3Activity::class.java
        )

        nextScreen.putExtra("companyEmail", companyEmail)
        nextScreen.putExtra("companyPass", companyPass)
        nextScreen.putExtra("companyPassConfirm", companyPassConfirm)
        nextScreen.putExtra("companyName", companyName)
        nextScreen.putExtra("companyComplementName", companyComplementName)
        nextScreen.putExtra("companyCnpj", companyCnpj)
        nextScreen.putExtra("companyPhone", companyPhone)


        startActivity(nextScreen)

    }
}
