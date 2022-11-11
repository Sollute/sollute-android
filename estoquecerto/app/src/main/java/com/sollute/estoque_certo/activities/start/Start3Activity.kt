package com.sollute.estoque_certo.activities.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.databinding.ActivityStart2Binding
import com.sollute.estoque_certo.databinding.ActivityStart3Binding

class Start3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityStart3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStart3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNextPageStart.setOnClickListener {
            irTelaStart4()
        }

        binding.goBack.setOnClickListener {
            irTelaStart2()
        }
    }

    private fun irTelaStart4() {
        val tela4 = Intent(
            this,
            Start3Activity::class.java
        )
        startActivity(tela4)
    }

    private fun irTelaStart2() {
        val tela2 = Intent(
            this,
            Start1Activity::class.java
        )
        startActivity(tela2)
    }
}