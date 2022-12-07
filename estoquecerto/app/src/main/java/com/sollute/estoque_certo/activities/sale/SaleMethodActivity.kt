package com.sollute.estoque_certo.activities.sale

import android.content.Intent
import android.os.Bundle
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.databinding.ActivitySaleMethodBinding

class SaleMethodActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivitySaleMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleMethodBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }

        binding.btnGoBack.setOnClickListener { goBack() }
        binding.ivPIX.setOnClickListener { next("PIX") }
        binding.iVMoney.setOnClickListener { next("Dinheiro") }
        binding.iVDebitCard.setOnClickListener { next("Cartão de Débito") }
        binding.iVCreditCard.setOnClickListener { next("Cartão de Crédito") }

        binding.tvSell.setOnClickListener { startActivity(Intent(this, AddSaleActivity::class.java)) }
        binding.tvDashboard.setOnClickListener { startActivity(Intent(this, DashboardActivity::class.java)) }
        binding.tvUser.setOnClickListener { startActivity(Intent(this, UserActivity::class.java)) }
        binding.tvProduct.setOnClickListener { startActivity(Intent(this, ProductActivity::class.java)) }
    }

    private fun goBack() = startActivity(Intent(this, AddSaleActivity::class.java))

    private fun next(
        paymentType: String,
    ) {
        val nextScreen: Intent = Intent(
            this,
            FinishSaleActivity::class.java
        ).apply {
            this.putExtra("paymentType", paymentType)
        }
        startActivity(nextScreen)
    }

}
