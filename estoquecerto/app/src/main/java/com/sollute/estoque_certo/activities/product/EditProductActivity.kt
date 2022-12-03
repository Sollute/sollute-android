package com.sollute.estoque_certo.activities.product

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.client.ClientActivity
import com.sollute.estoque_certo.activities.start.Start1Activity
import com.sollute.estoque_certo.databinding.ActivityEditProductBinding
import com.sollute.estoque_certo.models.product.EditProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProductActivity : DrawerBaseActivity() {

    lateinit var binding: ActivityEditProductBinding
    private val httpClient: Product = Rest.getInstance().create(Product::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProductBinding.inflate(layoutInflater)

        val name = intent.getStringExtra("productName")!!
        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener { goBack() }
        binding.btnEditProduct.setOnClickListener { edit(idEmpresa, name) }
        binding.btnDeleteProduct.setOnClickListener { confirmation(name, idEmpresa) }
        binding.btnMenuHamburguer.setOnClickListener { super.drawerLayout.open() }

        getInfo(name, idEmpresa)
    }

    private fun goBack() = startActivity(Intent(this, ProductActivity::class.java))

    private fun confirmation(
        productName: String,
        idEmpresa: Int
    ) = AlertDialog.Builder(this)
        .setTitle("Atenção")
        .setMessage("Tem certeza que deseja excluir o produto $productName ?")
        .setCancelable(false)
        .setPositiveButton("Excluir") { option, _ ->
            option.cancel()
            delete(idEmpresa, productName)
        }
        .setNegativeButton("Cancelar") { option, _ ->
            option.cancel()
            Toast.makeText(this, "Ação cancelada", Toast.LENGTH_SHORT).show()
        }.create().show()

    private fun getInfo(
        productName: String,
        idEmpresa: Int
    ) {
        httpClient.getInfo(productName, idEmpresa).enqueue(object : Callback<EditProduct> {
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

    private fun edit(
        idEmpresa: Int,
        productName: String
    ) {

        val prod = EditProduct(
            binding.etInitialInventory.text.toString().toInt(),
            binding.etMinimumStock.text.toString().toInt(),
            binding.etMaximumStock.text.toString().toInt(),
            binding.etPurchasePrice.text.toString().toDouble(),
            binding.etSalePrice.text.toString().toDouble(),
        )

        httpClient.editProduct(
            idEmpresa = idEmpresa,
            nome = productName,
            editProduct = prod
        ).enqueue(object : Callback<Void> {
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
                        ).show()
                        goBack()
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

    private fun delete(
        idEmpresa: Int,
        productName: String
    ) {

        httpClient.deleteProduct(productName, idEmpresa).enqueue(object : Callback<Void> {
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
                        ).show()
                        goBack()
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
