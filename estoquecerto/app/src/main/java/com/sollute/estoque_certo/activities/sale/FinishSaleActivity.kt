package com.sollute.estoque_certo.activities.sale

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.adapters.AdapterSaleCart
import com.sollute.estoque_certo.databinding.ActivityFinishSaleBinding
import com.sollute.estoque_certo.models.extract.NewExtract
import com.sollute.estoque_certo.models.sale.ListSaleProduct
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.extract.Extract
import com.sollute.estoque_certo.services.sale.Sale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FinishSaleActivity : DrawerBaseActivity() {

    private var amount: Double = 0.0
    private var quantityItems: Int = 0
    private lateinit var binding: ActivityFinishSaleBinding
    private val listSaleProducts: MutableList<ListSaleProduct> = mutableListOf()
    private val httpClientSale: Sale = Rest.getInstance().create(Sale::class.java)
    private val httpClientExtract: Extract = Rest.getInstance().create(Extract::class.java)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishSaleBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val paymentType = intent.extras!!.getString("paymentType")
        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        list(idEmpresa)

        binding.tvPayment.text = "Pagamento: $paymentType"

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.btnFinishSell.setOnClickListener { sell(idEmpresa) }
        binding.tvSell.setOnClickListener {
            startActivity(Intent(this, AddSaleActivity::class.java))
        }
        binding.tvDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        binding.tvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        binding.tvProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
    }

    private fun nextScreen() = startActivity(Intent(this, SaleCompleted::class.java))

    private fun list(
        idEmpresa: Int
    ) {

        val recyclerView = binding.rvProductListCart

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapterSaleProduct = AdapterSaleCart(this, listSaleProducts)

        httpClientSale.listCart(idEmpresa).enqueue(object : Callback<List<ListSaleProduct>> {

            override fun onResponse(
                call: Call<List<ListSaleProduct>>,
                response: Response<List<ListSaleProduct>>
            ) {
                when (response.code()) {
                    (200) -> {
                        listSaleProducts.clear()
                        for (index in response.body()!!) {
                            amount += index.valorVenda
                            quantityItems += index.qtdVenda
                            listSaleProducts.add(index)
                        }
                        binding.tvTotal.text = "Total: R$ ${amount}0"
                        recyclerView.adapter = adapterSaleProduct
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListSaleProduct>>,
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

    private fun sell(
        idEmpresa: Int
    ) {

        httpClientSale.sellCart(idEmpresa).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when (response.code()) {
                    200 -> {
                        Toast.makeText(
                            baseContext,
                            "Venda realizada com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        createExtract(idEmpresa)
                        nextScreen()
                    }
                    else -> {
                        Toast.makeText(baseContext, "NOT OK", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "NOT OK", Toast.LENGTH_LONG).show()
                nextScreen()
            }
        })

    }

    private fun createExtract(
        idEmpresa: Int
    ) {

        val newRecipe = NewExtract(
            extractName = "Venda de ${quantityItems} itens",
            extractTime = SimpleDateFormat("dd/M/yyyy      hh:mm:ss").format(Date()),
            extractAmount = amount,
            extract_type = 3
        )

        httpClientExtract.postExtract(idEmpresa, newRecipe).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) { print("ok") }
            override fun onFailure(call: Call<Void>, t: Throwable) { print("not ok") }
        })
    }
}
