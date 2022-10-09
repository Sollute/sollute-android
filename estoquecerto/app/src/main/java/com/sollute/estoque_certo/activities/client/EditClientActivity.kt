package com.sollute.estoque_certo.activities.client

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.activities.MainActivity
import com.sollute.estoque_certo.databinding.ActivityEditClientBinding

class EditClientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener { editNewClient() }

    }

    private fun editNewClient() {

        val clientName = binding.etClientName.text.toString()
        val clientPhone = binding.etClientPhone.text.toString()
        val clientCell = binding.etClientCell.text.toString()

        val nextScreen = Intent(
            this,
            MainActivity::class.java
        )

        startActivity(nextScreen)

    }
}