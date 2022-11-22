package com.sollute.estoque_certo.activities.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityNewProductFirstBinding

class NewProductFirstActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityNewProductFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductFirstBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.goBack.setOnClickListener { onBackPressed() }
        binding.btnNextPageRegisterProduct.setOnClickListener { nextStep() }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
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
        ).also {
            it.putExtra("productName", productName)
            it.putExtra("productCode", productCode)
            it.putExtra("productBrand", productBrand)
            it.putExtra("productCategory", productCategory)
            it.putExtra("productWeigth", productWeight)
            it.putExtra("productSize", productSize)
        }

        startActivity(nextScreen)
    }
}
