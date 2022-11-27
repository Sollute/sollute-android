package com.sollute.estoque_certo.activities.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.databinding.ActivityStart1Binding

class Start1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityStart1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStart1Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnStart.setOnClickListener { irTelaStart2() }
    }

    private fun irTelaStart2() {
        startActivity(Intent(this, Start2Activity::class.java))
    }
}
