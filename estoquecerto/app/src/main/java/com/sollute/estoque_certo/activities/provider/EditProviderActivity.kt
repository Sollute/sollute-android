package com.sollute.estoque_certo.activities.provider

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.activities.client.ClientActivity
import com.sollute.estoque_certo.databinding.ActivityEditProviderBinding
import com.sollute.estoque_certo.models.provider.EditProvider
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.provider.Provider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProviderActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityEditProviderBinding
    private val httpProvider: Provider = Rest.getInstance().create(Provider::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProviderBinding.inflate(layoutInflater)

        val providerName = intent.getStringExtra("providerName")!!
        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener { goBack() }
        binding.btnEditProvider.setOnClickListener { editProvider(idEmpresa) }
        binding.btnDeleteProvider.setOnClickListener { confirmation(providerName, idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack() = startActivity(Intent(this, ProviderActivity::class.java))

    private fun confirmation(
        providerName: String,
        idEmpresa: Int
    ) = AlertDialog.Builder(this)
        .also {
            it.setTitle("Atenção")
            it.setMessage("Tem certeza que deseja excluir o cliente $providerName ?")
            it.setPositiveButton("Excluir") { _, _ -> deleteProvider(idEmpresa) }
            it.setNegativeButton("Cancelar") { option, _ ->
                option.cancel()
                Toast.makeText(
                    this,
                    "Ação cancelada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.create().show()

    private fun editProvider(
        idEmpresa: Int
    ) {

        val editProvider = EditProvider(
            nomeFornecedor = binding.etProviderName.text.toString(),
            telefoneFornecedor = binding.etProviderPhone.text.toString(),
            nomeProduto = binding.etProviderProduct.text.toString()
        )

        httpProvider.editProvider(
            idEmpresa = idEmpresa,
            telefoneFornecedor = binding.etProviderPhone.text.toString(),
            editProvider = editProvider
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Fornecedor editado com sucesso",
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

    private fun deleteProvider(
        idEmpresa: Int
    ) {

        httpProvider.deleteProvider(
            idEmpresa = idEmpresa,
            telefoneFornecedor = binding.etProviderPhone.text.toString()
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Funcionário excluído com sucesso",
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
