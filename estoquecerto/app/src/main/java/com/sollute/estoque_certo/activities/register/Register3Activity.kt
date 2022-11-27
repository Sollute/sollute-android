package com.sollute.estoque_certo.activities.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.activities.login.Login
import com.sollute.estoque_certo.databinding.ActivityRegister3Binding
import com.sollute.estoque_certo.models.company.NewCompany
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.register.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister3Binding
    private val httpClient: Register = Rest.getInstance().create(Register::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.tvLogin.setOnClickListener { login() }
        binding.btnNextPageRegister.setOnClickListener { postCompany() }
        binding.checkboxTerms.setOnClickListener { validRegister() }
    }

    private fun login() = startActivity(Intent(this, Login::class.java))

    private fun validRegister() {
        val check = binding.btnNextPageRegister

        if (binding.checkboxTerms.isChecked) {
            check.setBackgroundColor(getColor(R.color.background_header))
            check.isEnabled = true
        } else {
            check.setBackgroundColor(getColor(R.color.background_text))
            check.isEnabled = false
        }
    }

    private fun postCompany() {

        val companyEmail = intent!!.getStringExtra("companyEmail")!!
        val companyPass = intent!!.getStringExtra("companyPass")!!
        val companyName = intent!!.getStringExtra("companyName")!!
        val companyComplementName = intent!!.getStringExtra("companyComplementName")!!
        val companyCnpj = intent!!.getStringExtra("companyCnpj")!!
        val companyCep = binding.etCep.text.toString()
        val companyUf = binding.etUf.text.toString()
        val companyCity = binding.etCidade.text.toString()
        val companyNeighborhood = binding.etLogradouro.text.toString()
        val companyReferencePoint = binding.etPontoRef.text.toString()

        val newCompany = NewCompany(
            email = companyEmail,
            senha = companyPass,
            nomeFantasia = companyName,
            razaoSocial = companyComplementName,
            cnpj = companyCnpj,
            qtdProdutosVendidos = 0,
            totalProdutosVendidos = 0.0,
            autenticado = false,
            cep = companyCep,
            uf = companyUf,
            cidade = companyCity,
            logradouro = companyNeighborhood,
            pontoReferencia = companyReferencePoint,
        )

        httpClient.postEmpresa(newCompany).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Empresa criada com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(baseContext, Login::class.java))
                    }
                    (response.code() == 409) -> {
                        Toast.makeText(
                            baseContext,
                            "Empresa já cadastrada",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    (response.code() == 400) -> {
                        Toast.makeText(
                            baseContext,
                            "ERRO 400",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                print("not ok")
            }
        })

    }
}
