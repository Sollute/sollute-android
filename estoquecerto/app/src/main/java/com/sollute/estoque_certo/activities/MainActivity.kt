package com.sollute.estoque_certo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.activities.product.NewProductFirstActivity
import com.sollute.estoque_certo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProductScreen.setOnClickListener { productScreen() }
        binding.btnExtractScreen.setOnClickListener { productExtract() }
    }

    private fun productScreen() {
        val productScreen = Intent(
            this,
            NewProductFirstActivity::class.java
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
}
