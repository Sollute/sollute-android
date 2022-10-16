package com.sollute.estoque_certo.activities.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.MainActivity
import com.sollute.estoque_certo.databinding.ActivityEditProductBinding
import com.sollute.estoque_certo.models.product.EditProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProductBinding
    lateinit var bundle: Bundle
    var nome: String = ""
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProductBinding.inflate(layoutInflater)
        bundle = getIntent().getExtras()!!
        nome = bundle.getString("productName")!!
        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener { goBack() }
        binding.btnDeleteProduct.setOnClickListener { delete() }
        binding.btnEditProduct.setOnClickListener { edit() }


        getInfo(nome)
    }

    private fun goBack() = startActivity(Intent(this, ProductActivity::class.java))

    private fun getInfo(name: String) {
        httpClient.getInfo(name, 1).enqueue(object : Callback<EditProduct> {
            override fun onResponse(
                call: Call<EditProduct>,
                response: Response<EditProduct>
            ) {
                when {
                    (response.isSuccessful) -> {
                        binding.etInitialInventory.setText(response.body()!!.estoque.toString())
                        binding.etMinimumStock.setText(response.body()!!.estoqueMin.toString())
                        binding.etMaximumStock.setText(response.body()!!.estoqueMax.toString())
                        binding.etPurchasePrice.setText(response.body()!!.precoCompra.toString())
                        binding.etSalePrice.setText(response.body()!!.precoVenda.toString())
                    }
                }
            }

            override fun onFailure(
                call: Call<EditProduct>,
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

    private fun edit() {

        val prod = EditProduct(
            binding.etInitialInventory.text.toString().toInt(),
            binding.etMinimumStock.text.toString().toInt(),
            binding.etMaximumStock.text.toString().toInt(),
            binding.etPurchasePrice.text.toString().toDouble(),
            binding.etSalePrice.text.toString().toDouble(),
        )

        httpClient.editProduct(1, nome, prod).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Produto editado com sucesso",
                            Toast.LENGTH_LONG
                        ).show().also {
                            goBack()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<Void>,
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

    private fun delete() {
        httpClient.deleteProduct(nome, 1).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Produto excluído com sucesso",
                            Toast.LENGTH_LONG
                        ).show().also {
                            goBack()
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<Void>,
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
