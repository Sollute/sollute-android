package com.sollute.estoque_certo.activities.product

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityNewProductSecondBinding
import com.sollute.estoque_certo.models.product.NewProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewProductSecondActivity : DrawerBaseActivity() {

    lateinit var binding: ActivityNewProductSecondBinding
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)
    private var isOnline: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductSecondBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)
        isOnline = getPreferences(MODE_PRIVATE).getBoolean("isOnline", true)

        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener { onBackPressed() }
        binding.btnFinishProduct.setOnClickListener { postProduct(idEmpresa, isOnline) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack(newProduct: NewProduct) {
        val productScreen = Intent(
            this,
            ProductActivity::class.java
        ).also {
            it.putExtra("product", newProduct)
        }

        startActivity(productScreen)
    }

    private fun postProduct(idEmpresa: Int, isOnline: Boolean) {

        val productCode = intent!!.getStringExtra("productCode")!!
        val productName = intent!!.getStringExtra("productName")!!
        val productBrand = intent!!.getStringExtra("productBrand")!!
        val productCategory = intent!!.getStringExtra("productCategory")!!
        val productWeight = intent!!.getDoubleExtra("productWeight", 0.0)
        val productSize = intent!!.getStringExtra("productSize")!!
        val initialInventory = binding.etInitialInventory.text.toString().toInt()
        val minimumStock = binding.etMinimumStock.text.toString().toInt()
        val maximumStock = binding.etMaximumStock.text.toString().toInt()
        val purchasePrice = binding.etPurchasePrice.text.toString().toDouble()
        val salePrice = binding.etSalePrice.text.toString().toDouble()

        val newProduct = NewProduct(
            codigo = productCode,
            nome = productName,
            marca = productBrand,
            categoria = productCategory,
            tamanho = productSize,
            peso = productWeight,
            precoCompra = purchasePrice,
            precoVenda = salePrice,
            estoque = initialInventory,
            estoqueMin = minimumStock,
            estoqueMax = maximumStock,
        )

        if (isOnline) {
            httpClient.postProduct(idEmpresa, newProduct).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    when {
                        (response.isSuccessful) -> {
                            Toast.makeText(
                                baseContext,
                                "Produto criado com sucesso",
                                Toast.LENGTH_LONG
                            ).show()
                            goBack(newProduct)
                        }
                        (response.code() == 409) -> {
                            Toast.makeText(
                                baseContext,
                                "Código ou nome já existe. Por favor, escolha outro",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        (response.code() == 400) -> {
                            Toast.makeText(
                                baseContext,
                                "ERRO 400",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    print("not ok")
                }

            })
        } else {
            goBack(newProduct)
        }
    }
}
