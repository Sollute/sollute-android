package com.sollute.estoque_certo.activities.provider

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityNewProviderBinding
import com.sollute.estoque_certo.models.provider.NewProvider
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.provider.Provider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewProviderActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityNewProviderBinding
    private val httpClient: Provider = Rest.getInstance().create(Provider::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProviderBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.btnFinishProvider.setOnClickListener { postProvider(idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack() {
        startActivity(Intent(this, ProviderActivity::class.java))
    }

    private fun postProvider(
        idEmpresa: Int
    ) {

        val newProvider = NewProvider(
            nomeFornecedor = binding.etProviderName.text.toString(),
            telefoneFornecedor = binding.etProviderPhone.text.toString(),
            nomeProduto = binding.etProviderProduct.text.toString(),
            qtd = binding.etProviderQuantity.text.toString().toInt()
        )

        httpClient.postProvider(
            idEmpresa = idEmpresa,
            newProvider = newProvider
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {

                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Fornecedor cadastrado com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack()
                    }
                    (response.code() == 409) -> {
                        Toast.makeText(
                            baseContext,
                            "Código ou nome já existe. Por favor, escolha outro",
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

            override fun onFailure(
                call: Call<Void>,
                t: Throwable
            ) {
                print("not ok")
            }

        })
    }
}
