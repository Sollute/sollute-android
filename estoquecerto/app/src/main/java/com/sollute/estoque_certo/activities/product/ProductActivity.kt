package com.sollute.estoque_certo.activities.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.adapters.AdapterProduct
import com.sollute.estoque_certo.databinding.ActivityProductBinding
import com.sollute.estoque_certo.models.product.ListProduct
import com.sollute.estoque_certo.models.product.NewProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityProductBinding
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)
    private val listProducts: MutableList<ListProduct> = mutableListOf()
    private var isOnline: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)
        isOnline = getPreferences(MODE_PRIVATE).getBoolean("isOnline", true)

        val productCreated = intent.extras?.getParcelable<NewProduct>("product")

        list(idEmpresa, isOnline, productCreated)

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.tvProduct.setOnClickListener { list(idEmpresa, isOnline, productCreated) }
        binding.tvPageName.setOnClickListener { list(idEmpresa, isOnline, productCreated) }

        binding.tvDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        binding.tvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        binding.tvNewProduct.setOnClickListener {
            startActivity(Intent(this, NewProductFirstActivity::class.java))
        }
    }

    private fun list(idEmpresa: Int, isOnline: Boolean, productCreated: NewProduct?) {

        val recyclerView = binding.rvProductList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapterProduct = AdapterProduct(this, listProducts) {
            Toast.makeText(baseContext, it.productName, Toast.LENGTH_SHORT).show()
            val productScreen = Intent(
                this,
                EditProductActivity::class.java
            )
            productScreen.putExtra("productName", it.productName)
            startActivity(productScreen)
        }

        if (isOnline) {
            httpClient.listProducts(idEmpresa).enqueue(object : Callback<List<ListProduct>> {

                override fun onResponse(
                    call: Call<List<ListProduct>>,
                    response: Response<List<ListProduct>>
                ) {
                    when {
                        (response.code() == 200) -> {
                            listProducts.clear()
                            for (index in response.body()!!) {
                                listProducts.add(index)
                            }
                            recyclerView.adapter = adapterProduct
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

        } else {
            if (productCreated != null) {
                listProducts.add(
                    ListProduct(
                        productName = productCreated.nome,
                        productPrice = productCreated.precoVenda,
                        productQuantity = productCreated.estoque
                    )
                ).also {
                    recyclerView.adapter = adapterProduct
                }
            } else {
                recyclerView.adapter = adapterProduct
                binding.tvYourProducts.text = "Você não possui produtos cadastrados"
                binding.SearchProduct.visibility = View.INVISIBLE
            }
        }

    }

}
