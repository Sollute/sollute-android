package com.sollute.estoque_certo.activities.client

import android.content.Intent
import android.os.Bundle
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.databinding.ActivityEditClientBinding
import com.sollute.estoque_certo.models.client.EditClient
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.client.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditClientActivity : DrawerBaseActivity() {
    lateinit var binding: ActivityEditClientBinding
    var nome: String = ""
    private val httpClient: Client = Rest.getInstance().create(Client::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditClientBinding.inflate(layoutInflater)

        nome = intent.getStringExtra("clientName")!!
        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener { goBack() }
        binding.btnMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
//        binding.btnEditClient.setOnClickListener { edit(idEmpresa) }

//        getInfo(idEmpresa)
    }

    private fun goBack() {
        startActivity(Intent(this, ClientActivity::class.java))
    }

//    private fun getInfo(
//        idEmpresa: Int
//    ) {
//        httpClient.listClients(idEmpresa).enqueue(object : Callback<EditClient> {
//            override fun onResponse(
//                call: Call<EditClient>,
//                response: Response<EditClient>
//            ) {
//                when {
//                    (response.isSuccessful) -> {
//                        binding.etClientName.setText(response.body()!!.nomeCliente)
//                        binding.etClientPhone.setText(response.body()!!.telefoneCliente)
//                        binding.etClientCell.setText(response.body()!!.celularCliente)
//                    }
//                }
//            }
//
//            override fun onFailure(
//                call: Call<EditClient>,
//                t: Throwable
//            ) {
//                Toast.makeText(
//                    baseContext,
//                    t.message,
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        })
//    }

//    private fun edit(idEmpresa: Int) {
//
//        val prod = EditClient(
//            binding.etClientName.text.toString(),
//            binding.etClientPhone.text.toString(),
//            binding.etClientCell.text.toString(),
//        )
//
//        httpClient.editClient(
//            idEmpresa = idEmpresa,
//            nome = nome,
//            editClient = prod
//        ).enqueue(object : Callback<Void> {
//            override fun onResponse(
//                call: Call<Void>,
//                response: Response<Void>
//            ) {
//                when {
//                    (response.isSuccessful) -> {
//                        Toast.makeText(
//                            baseContext,
//                            "Cliente editado com sucesso",
//                            Toast.LENGTH_LONG
//                        ).show()
//                        goBack(idEmpresa)
//                    }
//                }
//            }
//
//            override fun onFailure(
//                call: Call<Void>,
//                t: Throwable
//            ) {
//                Toast.makeText(
//                    baseContext,
//                    t.message,
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        })
//    }

}
