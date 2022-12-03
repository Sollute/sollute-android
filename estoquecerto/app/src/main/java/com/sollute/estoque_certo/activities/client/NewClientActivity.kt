package com.sollute.estoque_certo.activities.client

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityNewClientBinding
import com.sollute.estoque_certo.models.client.NewClient
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.client.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewClientActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityNewClientBinding
    private val httpClient: Client = Rest.getInstance().create(Client::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewClientBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.btnFinishClient.setOnClickListener { postClient(idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack() {
        startActivity(Intent(this, ClientActivity::class.java))
    }

    private fun postClient(
        idEmpresa: Int
    ) {

        val newClient = NewClient(
            nomeCliente = binding.etClientName.text.toString(),
            telefoneCliente = binding.etClientPhone.text.toString()
        )

        httpClient.postClient(
            idEmpresa = idEmpresa,
            newClient = newClient
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {

                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Cliente cadastrado com sucesso!",
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
