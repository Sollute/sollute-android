package com.sollute.estoque_certo.activities.extract

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.activities.user.UserActivity
import com.sollute.estoque_certo.adapters.AdapterExtract
import com.sollute.estoque_certo.databinding.ActivityExtractBinding
import com.sollute.estoque_certo.models.extract.ListExtract
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.extract.Extract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtractActivity : DrawerBaseActivity() {

    public lateinit var bindingExtract: ActivityExtractBinding
    private val httpClient: Extract = Rest.getInstance().create(Extract::class.java)
    private var isOnline: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)
        isOnline = getPreferences(MODE_PRIVATE).getBoolean("isOnline", true)

        bindingExtract = ActivityExtractBinding.inflate(layoutInflater)
        setContentView(bindingExtract.root)

        list(idEmpresa)

        bindingExtract.tvPageName.setOnClickListener { list(idEmpresa) }
        bindingExtract.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }

        bindingExtract.tvNewExtract.setOnClickListener {
            startActivity(Intent(this, NewRecipeActivity::class.java))
        }
        bindingExtract.tvProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
        bindingExtract.tvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun list(idEmpresa: Int) {
        val listExtract: MutableList<ListExtract> = mutableListOf()
        val recyclerView = bindingExtract.rvExtractList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapterExtract = AdapterExtract(this, listExtract)

        httpClient.list(idEmpresa).enqueue(object : Callback<List<ListExtract>> {

            override fun onResponse(
                call: Call<List<ListExtract>>,
                response: Response<List<ListExtract>>
            ) {
                when {
                    (response.code() == 200) -> {
                        for (index in response.body()!!) {
                            listExtract.add(index)
                        }
                        recyclerView.adapter = adapterExtract
                    }
                    (response.code() == 204) -> {
                        bindingExtract.tvTittleExtract.text =
                            "Você não possui nenhum histórico de lançamento"
                        bindingExtract.SearchExtract.visibility = View.INVISIBLE
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
