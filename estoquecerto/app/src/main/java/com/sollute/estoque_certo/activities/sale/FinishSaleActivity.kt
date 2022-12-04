package com.sollute.estoque_certo.activities.sale

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.databinding.ActivityFinishSaleBinding
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.sale.Sale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishSaleActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityFinishSaleBinding
    private val httpClientSale: Sale = Rest.getInstance().create(Sale::class.java)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishSaleBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val amount = intent.extras!!.getDouble("amount")
        val paymentType = intent.extras!!.getString("paymentType")
        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        binding.tvTotal.text = "Total: R$${amount}0"
        binding.tvPayment.text = "Pagamento: $paymentType"

        binding.btnFinishSell.setOnClickListener { sell(idEmpresa) }

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

    private fun sell(
        idEmpresa: Int
    ) {

        httpClientSale.sellCart(idEmpresa).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when (response.code()) {
                    200 -> {
                        Toast.makeText(baseContext, "Venda realizada com sucesso", Toast.LENGTH_LONG).show()
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

    private fun nextScreen() {
        startActivity(Intent(this, SaleCompleted::class.java))
    }
}
