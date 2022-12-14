package com.sollute.estoque_certo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.client.NewClientActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.activities.login.Login
import com.sollute.estoque_certo.activities.provider.NewProviderActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProductScreen.setOnClickListener { productScreen() }
        binding.btnExtractScreen.setOnClickListener { productExtract() }
        binding.btnNewProductScreen.setOnClickListener { clientScreen() }
        binding.btnLogin.setOnClickListener { loginScreen() }
        binding.btnNewProviderScreen.setOnClickListener { providerScreen() }
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

    private fun loginScreen() {
        val loginScreen = Intent(
            this,
            Login::class.java
        )
        startActivity(loginScreen)
    }

    private fun providerScreen() {
        val providerScreen = Intent(
            this,
            NewProviderActivity::class.java
        )
        startActivity(providerScreen)
    }
}
