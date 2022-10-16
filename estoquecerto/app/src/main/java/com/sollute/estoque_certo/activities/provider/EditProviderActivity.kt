package com.sollute.estoque_certo.activities.provider

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.activities.MainActivity
import com.sollute.estoque_certo.databinding.ActivityEditProviderBinding

class EditProviderActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEditProviderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener { createEditProvider() }

    }

    private fun createEditProvider() {

        val providerName = binding.etProviderName.text.toString()
        val providerProduct = binding.etProviderProduct.text.toString()
        val providerPhone = binding.etProviderPhone.text.toString()
        val providerCell = binding.etProviderCell.text.toString()

        val nextScreen = Intent(
            this,
            MainActivity::class.java
        )

        startActivity(nextScreen)

    }
}