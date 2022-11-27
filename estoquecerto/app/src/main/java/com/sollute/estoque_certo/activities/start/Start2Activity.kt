package com.sollute.estoque_certo.activities.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityStart2Binding

class Start2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityStart2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStart2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnNextPageStart.setOnClickListener { irTelaStart3() }
        binding.goBack.setOnClickListener { irTelaStart1() }
    }

    private fun irTelaStart3() {
        startActivity(Intent(this, Start3Activity::class.java))
    }

    private fun irTelaStart1() {
        startActivity(Intent(this, Start1Activity::class.java))
    }
}
