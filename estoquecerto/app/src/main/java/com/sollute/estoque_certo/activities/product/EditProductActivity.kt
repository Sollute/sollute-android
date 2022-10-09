package com.sollute.estoque_certo.activities.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityEditProductBinding

class EditProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackToStep1.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
    }
}
