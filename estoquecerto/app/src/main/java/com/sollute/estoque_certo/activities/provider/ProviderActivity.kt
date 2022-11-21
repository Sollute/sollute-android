package com.sollute.estoque_certo.activities.provider

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.client.EditClientActivity
import com.sollute.estoque_certo.activities.client.NewClientActivity
import com.sollute.estoque_certo.activities.employee.EmployeeActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.adapters.AdapterClient
import com.sollute.estoque_certo.adapters.AdapterProvider
import com.sollute.estoque_certo.databinding.ActivityProviderBinding
import com.sollute.estoque_certo.models.client.ListClient
import com.sollute.estoque_certo.models.provider.EditProvider
import com.sollute.estoque_certo.models.provider.ListProvider
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.provider.Provider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProviderBinding
    private val httpClient: Provider = Rest.getInstance().create(Provider::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idEmpresa = intent.getIntExtra("idEmp", 0)

        list(idEmpresa)
        binding.tvPageName.setOnClickListener { list(idEmpresa) }
        binding.tvProduct.setOnClickListener { list(idEmpresa) }
        binding.tvSell.setOnClickListener {
            val providerScreen = Intent(
                this,
                ExtractActivity::class.java
            )
            providerScreen.putExtra("idEmp", idEmpresa)
            startActivity(providerScreen)
        }
        binding.tvUser.setOnClickListener {
            val clientScreen = Intent(
                this,
                EmployeeActivity::class.java
            )
            clientScreen.putExtra("idEmp", idEmpresa)
            startActivity(clientScreen)
        }
        binding.tvNewProvider.setOnClickListener {
            val providerScreen = Intent(
                this,
                NewProviderActivity::class.java
            )
            providerScreen.putExtra("idEmp", idEmpresa)
            startActivity(providerScreen)
        }
    }

    private fun list(idEmpresa: Int) {
        val listProvider: MutableList<ListProvider> = mutableListOf()
        val reciclewView = binding.rvProviderList
        reciclewView.layoutManager = LinearLayoutManager(this)
        reciclewView.setHasFixedSize(true)

        val adapterProvider = AdapterProvider(this, listProvider) {
            Toast.makeText(baseContext, it.providerName, Toast.LENGTH_SHORT).show()
            val providerScreen = Intent(
                this,
                EditProviderActivity::class.java
            )
            providerScreen.putExtra("providerName", it.providerName)
            providerScreen.putExtra("idEmp", idEmpresa)
            startActivity(providerScreen)
        }

        with(httpClient) {

            listProvider(idEmpresa).enqueue(object : Callback<List<ListProvider>> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<List<ListProvider>>,
                    response: Response<List<ListProvider>>
                ) {
                    when {
                        (response.code() == 200) -> {
                            for (index in response.body()!!) {
                                listProvider.add(index)
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
}