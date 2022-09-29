package com.sollute.estoque_certo.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityEditProductSecondBinding

class EditProductSecondActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProductSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProductSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
