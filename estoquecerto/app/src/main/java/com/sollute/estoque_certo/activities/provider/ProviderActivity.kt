package com.sollute.estoque_certo.activities.provider

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.activities.employee.EmployeeActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.adapters.AdapterProvider
import com.sollute.estoque_certo.databinding.ActivityProviderBinding
import com.sollute.estoque_certo.models.provider.ListProvider
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.provider.Provider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityProviderBinding
    private val listProviders: MutableList<ListProvider> = mutableListOf()
    private val httpClient: Provider = Rest.getInstance().create(Provider::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idEmpresa = intent.getIntExtra("idEmp", 0)

        list(idEmpresa)
        binding.tvPageName.setOnClickListener { list(idEmpresa) }
        binding.tvProduct.setOnClickListener { list(idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.tvSell.setOnClickListener {
            startActivity(Intent(this, ExtractActivity::class.java))
        }
        binding.tvUser.setOnClickListener {
            startActivity(Intent(this, EmployeeActivity::class.java))
        }
        binding.tvNewProvider.setOnClickListener {
            startActivity(Intent(this, NewProviderActivity::class.java))
        }
    }

    private fun list(idEmpresa: Int) {

        val reciclewView = binding.rvProviderList

        reciclewView.layoutManager = LinearLayoutManager(this)
        reciclewView.setHasFixedSize(true)

        val adapterProvider = AdapterProvider(this, listProviders) {
            Toast.makeText(baseContext, it.nomeFornecedor, Toast.LENGTH_SHORT).show()
            val providerScreen = Intent(
                this,
                EditProviderActivity::class.java
            )
            providerScreen.putExtra("providerName", it.nomeFornecedor)
            startActivity(providerScreen)
        }

        httpClient.listProvider(idEmpresa).enqueue(object : Callback<List<ListProvider>> {

            override fun onResponse(
                call: Call<List<ListProvider>>,
                response: Response<List<ListProvider>>
            ) {
                when {
                    (response.code() == 200) -> {
                        listProviders.clear()
                        for (index in response.body()!!) {
                            listProviders.add(index)
                        }
                        reciclewView.adapter = adapterProvider
                    }
                    (response.code() == 204) -> {
                        binding.tvYourProvider.text = "Você não possui fornecedores cadastrados"
                        binding.SearchProvider.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListProvider>>,
                t: Throwable
            ) {
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }
}
