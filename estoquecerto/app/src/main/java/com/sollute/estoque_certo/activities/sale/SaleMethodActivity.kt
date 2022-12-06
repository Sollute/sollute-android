package com.sollute.estoque_certo.activities.sale

import android.content.Intent
import android.os.Bundle
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivitySaleMethodBinding

class SaleMethodActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivitySaleMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleMethodBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val amount = intent.extras!!.getDouble("amount")

        binding.btnGoBack.setOnClickListener { goBack() }
        binding.ivPIX.setOnClickListener { next("PIX", amount) }
        binding.iVMoney.setOnClickListener { next("Dinheiro", amount) }
        binding.iVDebitCard.setOnClickListener { next("Cartão de Débito", amount) }
        binding.iVCreditCard.setOnClickListener { next("Cartão de Crédito", amount) }
    }

    private fun goBack() = startActivity(Intent(this, AddSaleActivity::class.java))

    private fun next(
        paymentType: String,
        amount: Double
    ) {
        val nextScreen: Intent = Intent(
            this,
            FinishSaleActivity::class.java
        ).apply {
            this.putExtra("amount", amount)
            this.putExtra("paymentType", paymentType)
        }

        startActivity(nextScreen)
    }

}
