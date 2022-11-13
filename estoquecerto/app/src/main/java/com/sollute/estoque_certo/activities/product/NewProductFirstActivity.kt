package com.sollute.estoque_certo.activities.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.databinding.ActivityNewProductFirstBinding

class NewProductFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewProductFirstBinding
    var isOnline: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductFirstBinding.inflate(layoutInflater)

        isOnline = intent.getBooleanExtra("isOnline", true)
        val idEmpresa = intent.getIntExtra("idEmp", 0)
        setContentView(binding.root)

        binding.btnNextPageRegisterProduct.setOnClickListener { nextStep(idEmpresa, isOnline) }
        binding.goBack.setOnClickListener { onBackPressed() }
    }

    private fun nextStep(idEmpresa: Int, isOnline: Boolean) {

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

        nextScreen.putExtra("idEmp", idEmpresa)
        nextScreen.putExtra("isOnline", isOnline)

        nextScreen.putExtra("productName", productName)
        nextScreen.putExtra("productCode", productCode)
        nextScreen.putExtra("productBrand", productBrand)
        nextScreen.putExtra("productCategory", productCategory)
        nextScreen.putExtra("productWeigth", productWeight)
        nextScreen.putExtra("productSize", productSize)

        startActivity(nextScreen)

    }

}
