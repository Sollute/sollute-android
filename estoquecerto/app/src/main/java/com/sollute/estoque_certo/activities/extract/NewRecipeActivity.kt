package com.sollute.estoque_certo.activities.extract

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityNewRecipeBinding
import com.sollute.estoque_certo.models.extract.NewExtract
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.extract.Extract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class NewRecipeActivity : DrawerBaseActivity() {

    lateinit var binding: ActivityNewRecipeBinding
    private val httpClient: Extract = Rest.getInstance().create(Extract::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        binding.btnSave.setOnClickListener { postRecipe(idEmpresa) }
        binding.goBack.setOnClickListener { startActivity(Intent(this, ExtractActivity::class.java)) }
        binding.tvSwitch.setOnClickListener { startActivity(Intent(this, NewExpenseActivity::class.java)) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack() { startActivity(Intent(this, ExtractActivity::class.java)) }

    private fun postRecipe(idEmpresa: Int) {

        val newRecipe = NewExtract(
            extractName = binding.etRecipeName.text.toString(),
//            extractTime = "aaaa",
            extractTime = SimpleDateFormat("dd/M/yyyy      hh:mm:ss").format(Date()),
            extractAmount = binding.etRecipeValue.text.toString().toDouble(),
            extract_type = 2
        )

        httpClient.postExtract(idEmpresa, newRecipe).enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Receita cadastrada com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack()
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                print("not ok")

            }

        })

    }
}
