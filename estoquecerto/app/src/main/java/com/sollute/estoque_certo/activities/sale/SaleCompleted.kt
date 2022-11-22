package com.sollute.estoque_certo.activities.sale

import android.os.Bundle
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivitySaleCompletedBinding

class SaleCompleted : DrawerBaseActivity() {

    private lateinit var binding: ActivitySaleCompletedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaleCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }
}
