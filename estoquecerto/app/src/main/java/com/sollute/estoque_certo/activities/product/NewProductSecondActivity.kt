package com.sollute.estoque_certo.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityNewProductSecondBinding

class NewProductSecondActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewProductSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewProductSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
