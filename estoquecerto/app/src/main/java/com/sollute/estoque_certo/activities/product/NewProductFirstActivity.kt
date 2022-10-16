package com.sollute.estoque_certo.activities.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.databinding.ActivityNewProductFirstBinding

class NewProductFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewProductFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextPageRegisterProduct.setOnClickListener { nextStep() }
        binding.goBack.setOnClickListener { onBackPressed() }
    }

    private fun nextStep() {

        val productName = binding.etProductName.text.toString()
        val productCode = binding.etProductCode.text.toString()
        val productBrand = binding.etProductBrand.text.toString()
        val productCategory = binding.etProductCategory.text.toString()
        val productWeight = binding.etWeight.text.toString()
        val productSize = binding.etSize.text.toString()

        val nextScreen = Intent(
            this,
            NewProductSecondActivity::class.java
        )

        nextScreen.putExtra("productName", productName)
        nextScreen.putExtra("productCode", productCode)
        nextScreen.putExtra("productBrand", productBrand)
        nextScreen.putExtra("productCategory", productCategory)
        nextScreen.putExtra("productWeigth", productWeight)
        nextScreen.putExtra("productSize", productSize)

        startActivity(nextScreen)

    }

}
