package com.sollute.estoque_certo.activities.product

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.adapters.AdapterProduct
import com.sollute.estoque_certo.databinding.ActivityProductBinding
import com.sollute.estoque_certo.models.product.ListProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPageName.setOnClickListener { list() }
        binding.tvProduct.setOnClickListener { list() }
        list()

        binding.tvNewProduct.setOnClickListener {
            startActivity(
                Intent(this, NewProductFirstActivity::class.java)
            )
        }

    }

    private fun list() {
        val listProducts: MutableList<ListProduct> = mutableListOf()
        val reciclewView = binding.rvProductList
        reciclewView.layoutManager = LinearLayoutManager(this)
        reciclewView.setHasFixedSize(true)

        val adapterProduct = AdapterProduct(this, listProducts) {
            Toast.makeText(baseContext, it.productName, Toast.LENGTH_SHORT).show()
            val productScreen = Intent(
                this,
                EditProductActivity::class.java
            )
            productScreen.putExtra("productName", it.productName)
            startActivity(productScreen)
        }

        httpClient.listProducts(1).enqueue(object : Callback<List<ListProduct>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<ListProduct>>,
                response: Response<List<ListProduct>>
            ) {
                when {
                    (response.code() == 200) -> {
                        for (index in response.body()!!) {
                            listProducts.add(index)
                        }
                        reciclewView.adapter = adapterProduct
                    }
                    (response.code() == 204) -> {
                        binding.tvYourProducts.text = "Você não possui produtos cadastrados"
                        binding.SearchProduct.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListProduct>>,
                t: Throwable
            ) {
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

}
