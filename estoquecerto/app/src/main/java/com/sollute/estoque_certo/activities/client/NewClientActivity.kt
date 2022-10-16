package com.sollute.estoque_certo.activities.client

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.activities.MainActivity
import com.sollute.estoque_certo.databinding.ActivityNewClientBinding
import com.sollute.estoque_certo.models.client.NewClient
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.client.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewClientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinished.setOnClickListener { createNewClient() }

    }

    private fun createNewClient() {

        val clientName = binding.etClientName.text.toString()
        val clientPhone = binding.etClientPhone.text.toString()
        val clientCell = binding.etClientCell.text.toString()

        val newClient = NewClient(
            nomeCliente = clientName,
            telefoneCliente = clientPhone,
            celularCliente = clientCell,
        )

        val request = Rest.getInstance().create(Client::class.java)

        request.postClient(ID_EMPRESA, newClient).enqueue(object :
            Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Cliente criado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    (response.code() == 400) -> {
                        print("ERROR CODE: 400")
                        print("ERROR CODE: 400")
                        print("ERROR CODE: 400")
                        print("ERROR CODE: 400")
                    }
                }

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                print("not ok")
            }

        })


        val nextScreen = Intent(
            this,
            MainActivity::class.java
        )

        startActivity(nextScreen)

    }

    companion object {
        const val ID_EMPRESA = 1L
    }
}