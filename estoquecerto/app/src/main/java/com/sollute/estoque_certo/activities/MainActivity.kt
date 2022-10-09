package com.sollute.estoque_certo.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.activities.client.NewClientActivity

import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSupportActionBar()?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProductScreen.setOnClickListener { productScreen() }
        binding.btnExtractScreen.setOnClickListener { productExtract() }
        binding.btnNewProductScreen.setOnClickListener { clientScreen() }

    }

    private fun productScreen() {
        val productScreen = Intent(
            this,
            ProductActivity::class.java
        )
        startActivity(productScreen)
    }

    private fun productExtract() {
        val productExtract = Intent(
            this,
            ExtractActivity::class.java
        )
        startActivity(productExtract)
    }

    private fun clientScreen() {
        val productScreen = Intent(
            this,
            NewClientActivity::class.java
        )
        startActivity(productScreen)
    }
}
