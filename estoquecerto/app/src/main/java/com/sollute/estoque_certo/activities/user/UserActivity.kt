package com.sollute.estoque_certo.activities.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.dashboard.DashboardActivity
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.sale.AddSaleActivity
import com.sollute.estoque_certo.databinding.ActivityUserBinding
import com.sollute.estoque_certo.models.user.UserResponse
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityUserBinding
    private val httpClient: User = Rest.getInstance().create(User::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

//        getInfo(idEmpresa)

        binding.tvSell.setOnClickListener {
            startActivity(Intent(this, AddSaleActivity::class.java))
        }
        binding.tvDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        binding.tvProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

    }

    private fun getInfo(idEmpresa: Int) {
        httpClient.getInfo(idEmpresa).enqueue(object : Callback<UserResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                binding.tvNomeFantasia.text = "${binding.tvNomeFantasia.text} + ${response.body()!!.nomeFantasia}"
                binding.tvRazaoSocial.text = "${binding.tvRazaoSocial.text} + ${response.body()!!.razaoSocial}"
                binding.tvCNPJ.text = "${binding.tvCNPJ.text} + ${response.body()!!.cnpj}"
                binding.tvEmail.text = "${binding.tvEmail.text} + ${response.body()!!.email}"
                binding.tvLogradouro.text = "${binding.tvLogradouro.text} + ${response.body()!!.logradouro}"
                binding.tvCidade.text = "${binding.tvCidade.text} + ${response.body()!!.cidade}"
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
