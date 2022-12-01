package com.sollute.estoque_certo.activities.client

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityEditClientBinding
import com.sollute.estoque_certo.models.client.EditClient
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.client.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditClientActivity : DrawerBaseActivity() {

    lateinit var binding: ActivityEditClientBinding
    private val httpClient: Client = Rest.getInstance().create(Client::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditClientBinding.inflate(layoutInflater)

        val clientName = intent.getStringExtra("clientName")!!
        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener { goBack() }
        binding.btnEditClient.setOnClickListener { edit(idEmpresa, clientName) }
        binding.btnMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.btnDeleteClient.setOnClickListener { confirmation(clientName, idEmpresa) }

//        getInfo(idEmpresa)
    }

    private fun goBack() = startActivity(Intent(this, ClientActivity::class.java))

    private fun confirmation(
        clientName: String,
        idEmpresa: Int
    ) = AlertDialog.Builder(this)
        .setTitle("Atenção")
        .setMessage("Tem certeza que deseja excluir o cliente $clientName ?")
        .setPositiveButton("Excluir") { option, _ ->
            option.cancel()
            delete(idEmpresa, clientName)
        }
        .setNegativeButton("Cancelar") { option, _ ->
            option.cancel()
            Toast.makeText(this, "Ação cancelada", Toast.LENGTH_SHORT).show()
        }
        .create().show()


//    private fun getInfo(
//        idEmpresa: Int
//    ) {
//
////        Precisa fazer um endpoint para recuperar as informações do cliente
////        Precisa fazer um endpoint para recuperar as informações do cliente
////        Precisa fazer um endpoint para recuperar as informações do cliente
////        Precisa fazer um endpoint para recuperar as informações do cliente
//
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


    private fun edit(
        idEmpresa: Int,
        clientName: String
    ) {

        val editClient = EditClient(
            binding.etClientName.text.toString(),
            binding.etClientPhone.text.toString(),
            binding.etClientCell.text.toString(),
        )

        httpClient.editClient(
            idEmpresa = idEmpresa,
            nome = clientName,
            editClient = editClient
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Cliente editado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack()
                    }
                }
            }

            override fun onFailure(
                call: Call<Void>,
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

    private fun delete(
        idEmpresa: Int,
        clientName: String
    ) {

        httpClient.deleteClient(
            clientName,
            idEmpresa
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Cliente excluído com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack()
                    }
                }
            }

            override fun onFailure(
                call: Call<Void>,
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
