package com.sollute.estoque_certo.activities.extract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityNewRecipeBinding

class NewRecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
