package com.sollute.estoque_certo.activities.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.extract.ExtractActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.databinding.ActivityEmployeeBinding

class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        setContentView(binding.root)

        binding.tvNewEmployee.setOnClickListener {
            val productScreen = Intent(
                this,
                NewEmployeeActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
        binding.tvProduct.setOnClickListener {
            val productScreen = Intent(
                this,
                ProductActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
        binding.tvSell.setOnClickListener {
            val productScreen = Intent(
                this,
                ExtractActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
    }
}
