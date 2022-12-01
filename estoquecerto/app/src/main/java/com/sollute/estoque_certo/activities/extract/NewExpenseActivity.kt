package com.sollute.estoque_certo.activities.extract

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.databinding.ActivityNewExpenseBinding

class NewExpenseActivity : DrawerBaseActivity() {

    lateinit var binding: ActivityNewExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener {
            startActivity(Intent(this, ExtractActivity::class.java))
        }
        binding.tvSwitch.setOnClickListener {
            startActivity(Intent(this, NewRecipeActivity::class.java))
        }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }
}
