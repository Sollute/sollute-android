package com.sollute.estoque_certo.activities.sale

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.adapters.AdapterProduct
import com.sollute.estoque_certo.databinding.ActivityAddSaleBinding
import com.sollute.estoque_certo.models.product.ListProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import com.sollute.estoque_certo.services.sale.Sale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSaleActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityAddSaleBinding
    private val listProducts: MutableList<ListProduct> = mutableListOf()
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)
    private val httpClientSale: Sale = Rest.getInstance().create(Sale::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        list(idEmpresa)

        binding.btnNextPage.setOnClickListener { nextPage() }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.tvSell.setOnClickListener { startActivity(Intent(this, AddSaleActivity::class.java)) }
        binding.tvDashboard.setOnClickListener { startActivity(Intent(this, DashboardActivity::class.java)) }
        binding.tvUser.setOnClickListener { startActivity(Intent(this, UserActivity::class.java)) }
        binding.tvProduct.setOnClickListener { startActivity(Intent(this, ProductActivity::class.java)) }
    }

    private fun nextPage() {
        val nextScreen = Intent(
            this,
            SaleMethodActivity::class.java
        )
        startActivity(nextScreen)
    }

    private fun addCart(
        idEmpresa: Int,
        productName: String,
        productQuantity: Int
    ) {

        httpClientSale.addCart(
            idEmpresa = idEmpresa,
            productName = productName,
            productQuantity = productQuantity
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(baseContext, "Produto adicionado ao carrinho", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "NOT OK", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun list(
        idEmpresa: Int
    ) {

        val recyclerView = binding.rvProductList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapterProduct = AdapterProduct(this, listProducts) {

            val product = ListProduct(
                it.productName,
                it.productPrice,
                it.productQuantity
            )

            val alertDialogView: View =
                LayoutInflater.from(baseContext).inflate(R.layout.activity_sale_dialog, null)
            val dialogBuilder = AlertDialog.Builder(this)
                .setView(alertDialogView)
                .setTitle("Adicionar ao carrinho")
            val alertDialog = dialogBuilder.show()

            alertDialogView.findViewById<Button>(R.id.btnSaveProduct).setOnClickListener {

                val requestQuantity = alertDialogView.findViewById<EditText>(R.id.etProductQuantity).text.toString().toInt()

                if ((product.productQuantity - requestQuantity) > 0) {
                    alertDialog.dismiss()
                    addCart(idEmpresa, product.productName, requestQuantity)
                }
            }

            alertDialogView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                alertDialog.dismiss()
            }

        }

        httpClient.listProducts(idEmpresa).enqueue(object : Callback<List<ListProduct>> {

            override fun onResponse(
                call: Call<List<ListProduct>>,
                response: Response<List<ListProduct>>
            ) {
                when (response.code()) {
                    200 -> {
                        listProducts.clear()
                        for (index in response.body()!!) {
                            listProducts.add(index)
                        }
                        recyclerView.adapter = adapterProduct
                    }
                    204 -> {
                        binding.LLRecycler.visibility = View.INVISIBLE
                        binding.btnNextPage.visibility = View.INVISIBLE
                        binding.tvLabelProdutos.text = "Você não possui produtos cadastrados"
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
