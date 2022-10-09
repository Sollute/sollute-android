package com.sollute.estoque_certo.activities.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.adapters.AdapterProduct
import com.sollute.estoque_certo.databinding.ActivityProductBinding
import com.sollute.estoque_certo.models.product.ListProduct

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reciclewView = binding.rvProductList
        reciclewView.layoutManager = LinearLayoutManager(this)
        reciclewView.setHasFixedSize(true)

        val listProducts: MutableList<ListProduct> = mutableListOf()
        val adapterProduct = AdapterProduct(this, listProducts) {
            val productScreen = Intent(
                this,
                EditProductActivity::class.java
            )
            startActivity(productScreen)
        }

        binding.tvNewProduct.setOnClickListener {
            startActivity(Intent(this, NewProductFirstActivity::class.java))
        }

        reciclewView.adapter = adapterProduct

        val product1 = ListProduct(
            productPhoto = R.drawable.item,
            productName = "Camiseta Nike",
            productPrice = 49.99,
            productQuantity = 10
        )

        val product2 = ListProduct(
            productPhoto = R.drawable.item,
            productName = "Calça Adidas",
            productPrice = 89.99,
            productQuantity = 8
        )

        val product3 = ListProduct(
            productPhoto = R.drawable.item,
            productName = "Meias",
            productPrice = 49.99,
            productQuantity = 50
        )

        listProducts.add(product1)
        listProducts.add(product2)
        listProducts.add(product3)
        listProducts.add(product1)
        listProducts.add(product2)
        listProducts.add(product3)
    }

}
