package com.sollute.estoque_certo.activities.product

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sollute.estoque_certo.databinding.ActivityNewProductSecondBinding
import com.sollute.estoque_certo.models.product.NewProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewProductSecondActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewProductSecondBinding
    var bundle: Bundle? = null
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductSecondBinding.inflate(layoutInflater)
        bundle = getIntent().getExtras()!!

        val idEmpresa = bundle?.getInt("idEmp", 0)
        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener { onBackPressed() }
        binding.btnFinishProduct.setOnClickListener { postProduct(idEmpresa!!) }
    }

    private fun goBack() { startActivity(Intent(this, ProductActivity::class.java)) }

    private fun postProduct(idEmpresa: Int) {

        val productCode = bundle!!.getString("productCode")!!
        val productName = bundle!!.getString("productName")!!
        val productBrand = bundle!!.getString("productBrand")!!
        val productCategory = bundle!!.getString("productCategory")!!
        val productWeight = bundle!!.getDouble("productWeight")
        val productSize = bundle!!.getString("productSize")!!
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

        httpClient.postProduct(idEmpresa, newProduct).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Produto criado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
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
    }

}
