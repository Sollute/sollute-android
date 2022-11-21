package com.sollute.estoque_certo.activities.client

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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

class ClientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientBinding
    private val httpClient: Client = Rest.getInstance().create(Client::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        list(idEmpresa)
        binding.tvPageName.setOnClickListener { list(idEmpresa) }
        binding.tvProduct.setOnClickListener { list(idEmpresa) }
        binding.tvSell.setOnClickListener {
            val clientScreen = Intent(
                this,
                ExtractActivity::class.java
            )
            clientScreen.putExtra("idEmp", idEmpresa)
            startActivity(clientScreen)
        }
        binding.tvUser.setOnClickListener {
            val clientScreen = Intent(
                this,
                EmployeeActivity::class.java
            )
            clientScreen.putExtra("idEmp", idEmpresa)
            startActivity(clientScreen)
        }
        binding.tvNewClient.setOnClickListener {
            val clientScreen = Intent(
                this,
                NewClientActivity::class.java
            )
            clientScreen.putExtra("idEmp", idEmpresa)
            startActivity(clientScreen)
        }
    }

    private fun list(idEmpresa: Int) {
        val listClients: MutableList<ListClient> = mutableListOf()
        val reciclewView = binding.rvClientList
        reciclewView.layoutManager = LinearLayoutManager(this)
        reciclewView.setHasFixedSize(true)

        val adapterClient = AdapterClient(this, listClients) {
            Toast.makeText(baseContext, it.clientName, Toast.LENGTH_SHORT).show()
            val clientScreen = Intent(
                this,
                EditClientActivity::class.java
            )
            clientScreen.putExtra("clientName", it.clientName)
            clientScreen.putExtra("idEmp", idEmpresa)
            startActivity(clientScreen)
        }

        with(httpClient) {

            listClients(idEmpresa).enqueue(object : Callback<List<ListClient>> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<List<ListClient>>,
                    response: Response<List<ListClient>>
                ) {
                    when {
                        (response.code() == 200) -> {
                            for (index in response.body()!!) {
                                listClients.add(index)
                            }
                            reciclewView.adapter = adapterClient
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
}