package com.sollute.estoque_certo.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityEditProductFirstBinding

class EditProductFirstActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProductFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProductFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}