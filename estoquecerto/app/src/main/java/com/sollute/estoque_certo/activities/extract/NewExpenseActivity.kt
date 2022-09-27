package com.sollute.estoque_certo.activities.extract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityNewExpenseBinding

class NewExpenseActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}