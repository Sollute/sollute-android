package com.sollute.estoque_certo.activities.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityStart3Binding

class Start3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityStart3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStart3Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnNextStep4.setOnClickListener { irTelaStart4() }
        binding.btnBackToStep2.setOnClickListener { irTelaStart2() }
    }

    private fun irTelaStart4() = startActivity(Intent(this, Start4Activity::class.java))

    private fun irTelaStart2() = startActivity(Intent(this, Start1Activity::class.java))

}
