package com.sollute.estoque_certo.activities.extract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityExtractBinding

class ExtractActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtractBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExtractBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
