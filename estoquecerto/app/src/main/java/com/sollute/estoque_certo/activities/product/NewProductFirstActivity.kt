package com.sollute.estoque_certo.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityNewProductFirstBinding

class NewProductFirstActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewProductFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
