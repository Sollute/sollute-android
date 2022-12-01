package com.sollute.estoque_certo.activities.employee

import android.content.Intent
import android.os.Bundle
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.databinding.ActivityEmployeeBinding

class EmployeeActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.tvNewEmployee.setOnClickListener {
            startActivity(Intent(this, NewEmployeeActivity::class.java))
        }
        binding.tvProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
        binding.tvSell.setOnClickListener {
            startActivity(Intent(this, ExtractActivity::class.java))
        }
    }

}
