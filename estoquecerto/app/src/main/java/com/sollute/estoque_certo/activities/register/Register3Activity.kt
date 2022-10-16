package com.sollute.estoque_certo.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    var bundle: Bundle? = null
    private val httpClient: Register = Rest.getInstance().create(Register::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegister3Binding.inflate(layoutInflater)
        bundle = intent.extras!!
        setContentView(binding.root)

        binding.btnNextPageRegisterProduct.setOnClickListener { postCompany() }
    }

    private fun postCompany() {

        val companyEmail = bundle!!.getString("companyEmail")!!
        val companyPass = bundle!!.getString("companyPass")!!
        val companyPassConfirm = bundle!!.getString("companyPassConfirm")!!
        val companyName = bundle!!.getString("companyName")!!
        val companyComplementName = bundle!!.getString("companyComplementName")!!
        val companyCnpj = bundle!!.getString("companyCnpj")!!
        val companyPhone = bundle!!.getString("companyPhone")!!
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
