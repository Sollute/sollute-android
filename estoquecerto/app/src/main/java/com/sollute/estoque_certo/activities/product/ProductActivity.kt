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

    private val httpClient: Product = Rest.getInstance().create(Product::class.java)
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        list(idEmpresa)
        binding.tvPageName.setOnClickListener { list(idEmpresa) }
        binding.tvProduct.setOnClickListener { list(idEmpresa) }
        binding.tvNewProduct.setOnClickListener {
            val productScreen = Intent(
                this,
                NewProductFirstActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
    }


    private fun list(idEmpresa: Int) {
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
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }

        httpClient.listProducts(idEmpresa).enqueue(object : Callback<List<ListProduct>> {
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
                        binding.tvYourProducts.text = "Voc?? n??o possui produtos cadastrados"
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
