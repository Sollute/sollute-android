package com.sollute.estoque_certo.activities.extract

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.employee.EmployeeActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.adapters.AdapterExtract
import com.sollute.estoque_certo.databinding.ActivityExtractBinding
import com.sollute.estoque_certo.models.extract.ListExtract
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.extract.Extract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtractActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtractBinding
    private val httpClient: Extract = Rest.getInstance().create(Extract::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        binding = ActivityExtractBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPageName.setOnClickListener { list(idEmpresa) }
        binding.tvNewExtract.setOnClickListener {
            val productScreen = Intent(
                this,
                NewRecipeActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
        binding.tvProduct.setOnClickListener {
            val productScreen = Intent(
                this,
                ProductActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
        binding.tvUser.setOnClickListener {
            val productScreen = Intent(
                this,
                EmployeeActivity::class.java
            )
            productScreen.putExtra("idEmp", idEmpresa)
            startActivity(productScreen)
        }
    }

    private fun list(idEmpresa: Int) {
        val listExtract: MutableList<ListExtract> = mutableListOf()
        val reciclewView = binding.rvExtractList
        reciclewView.layoutManager = LinearLayoutManager(this)
        reciclewView.setHasFixedSize(true)

        val adapterExtract = AdapterExtract(this, listExtract)

        httpClient.list(idEmpresa).enqueue(object : Callback<List<ListExtract>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<ListExtract>>,
                response: Response<List<ListExtract>>
            ) {
                when {
                    (response.code() == 200) -> {
                        for (index in response.body()!!) {
                            listExtract.add(index)
                        }
                        reciclewView.adapter = adapterExtract
                    }
                    (response.code() == 204) -> {
                        binding.tvTittleExtract.text =
                            "Você não possui nenhum histórico de lançamento"
                        binding.SearchExtract.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListExtract>>,
                t: Throwable
            ) {
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

}
