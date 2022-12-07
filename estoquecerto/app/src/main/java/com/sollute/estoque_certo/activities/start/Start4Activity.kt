package com.sollute.estoque_certo.activities.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sollute.estoque_certo.activities.register.RegisterActivity
import com.sollute.estoque_certo.databinding.ActivityStart4Binding

class Start4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityStart4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStart4Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnFinish.setOnClickListener { irTelaRegister() }
        binding.btnBackToStep3.setOnClickListener { irTelaStart3() }
    }

    private fun irTelaRegister() = startActivity(Intent(this, RegisterActivity::class.java))

    private fun irTelaStart3() = startActivity(Intent(this, Start1Activity::class.java))

}
