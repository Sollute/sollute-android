package com.sollute.estoque_certo.activities.provider

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.activities.MainActivity
import com.sollute.estoque_certo.activities.client.ClientActivity
import com.sollute.estoque_certo.databinding.ActivityNewProviderBinding
import com.sollute.estoque_certo.models.client.NewClient
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
        val idEmpresa = intent.getIntExtra("idEmp", 0)
        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.btnFinishProvider.setOnClickListener { postProvider(idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack(idEmpresa: Int) {
        val providerScreen = Intent(
            this,
            ClientActivity::class.java
        )
        providerScreen.putExtra("idEmp", idEmpresa)
        startActivity(providerScreen)
    }

    private fun postProvider(idEmpresa: Int) {

        val providerName = binding.etProviderName.text.toString()
        val providerProduct = binding.etProviderProduct.text.toString()
        val providerPhone = binding.etProviderPhone.text.toString()

        val newProvider = NewProvider(
            nomeFornecedor = providerName,
            telefoneFornecedor = providerPhone,
            produtoFornecedor = providerProduct
        )

        httpClient.postProvider(idEmpresa, newProvider).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Fornecedor cadastrado com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack(idEmpresa)
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

            override fun onFailure(call: Call<Void>, t: Throwable) {
                print("not ok")
            }

        })
    }
}