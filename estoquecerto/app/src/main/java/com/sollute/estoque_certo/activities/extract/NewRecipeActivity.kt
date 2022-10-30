package com.sollute.estoque_certo.activities.extract

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.product.NewProductFirstActivity
import com.sollute.estoque_certo.databinding.ActivityNewRecipeBinding

class NewRecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        binding.goBack.setOnClickListener {
            val productScreen = Intent(
                this,
                ExtractActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }

        binding.tvSwitch.setOnClickListener {
            val productScreen = Intent(
                this,
                NewExpenseActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
    }

}
