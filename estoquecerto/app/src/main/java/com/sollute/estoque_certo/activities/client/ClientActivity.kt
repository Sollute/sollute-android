package com.sollute.estoque_certo.activities.client

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.employee.EmployeeActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.adapters.AdapterClient
import com.sollute.estoque_certo.databinding.ActivityClientBinding
import com.sollute.estoque_certo.models.client.ListClient
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.client.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityClientBinding
    private val listClients: MutableList<ListClient> = mutableListOf()
    private val httpClient: Client = Rest.getInstance().create(Client::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

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
        binding.tvNewClient.setOnClickListener {
            startActivity(Intent(this, NewClientActivity::class.java))
        }
    }

    private fun list(
        idEmpresa: Int
    ) {

        val recyclerView = binding.rvClientList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapterClient = AdapterClient(this, listClients) {
            Toast.makeText(baseContext, it.nomeCliente, Toast.LENGTH_SHORT).show()
            val clientScreen = Intent(
                this,
                EditClientActivity::class.java
            )
            clientScreen.putExtra("clientName", it.nomeCliente)
            startActivity(clientScreen)
        }

        httpClient.listClients(idEmpresa).enqueue(object : Callback<List<ListClient>> {

            override fun onResponse(
                call: Call<List<ListClient>>,
                response: Response<List<ListClient>>
            ) {
                when {
                    (response.code() == 200) -> {
                        listClients.clear()
                        for (index in response.body()!!) {
                            listClients.add(index)
                        }
                        recyclerView.adapter = adapterClient
                    }
                    (response.code() == 204) -> {
                        binding.tvYourClients.text = "Você não possui clientes cadastrados"
                        binding.SearchClient.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListClient>>,
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
