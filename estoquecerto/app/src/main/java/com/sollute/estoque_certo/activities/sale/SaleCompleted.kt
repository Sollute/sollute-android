package com.sollute.estoque_certo.activities.sale

import android.content.Intent
import android.os.Bundle
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.databinding.ActivitySaleCompletedBinding

class SaleCompleted : DrawerBaseActivity() {

    private lateinit var binding: ActivitySaleCompletedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaleCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnYes.setOnClickListener { startActivity(Intent(this, AddSaleActivity::class.java)) }
        binding.btnNo.setOnClickListener { startActivity(Intent(this, ProductActivity::class.java)) }

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.tvDashboard.setOnClickListener { startActivity(Intent(this, DashboardActivity::class.java)) }
        binding.tvUser.setOnClickListener { startActivity(Intent(this, UserActivity::class.java)) }
        binding.tvProduct.setOnClickListener { startActivity(Intent(this, ProductActivity::class.java)) }
    }
}
