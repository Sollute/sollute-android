package com.sollute.estoque_certo.activities.employee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.activities.product.ProductActivity
import com.sollute.estoque_certo.adapters.AdapterEmployee
import com.sollute.estoque_certo.databinding.ActivityEmployeeBinding
import com.sollute.estoque_certo.models.employee.ListEmployee
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.employee.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    private val listEmployee: MutableList<ListEmployee> = mutableListOf()
    private val httpClient: Employee = Rest.getInstance().create(Employee::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        list(idEmpresa)

        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
        binding.tvNewEmployee.setOnClickListener {
            startActivity(Intent(this, NewEmployeeActivity::class.java))
        }
        binding.tvProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
    }

    private fun list(
        idEmpresa: Int
    ) {
        val recyclerView = binding.rvEmployeeList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapterClient = AdapterEmployee(this, listEmployee) {
            Toast.makeText(baseContext, it.nomeFuncionario, Toast.LENGTH_SHORT).show()
            val employeeScreen = Intent(
                this,
                EditEmployeeActivity::class.java
            ).apply {
                this.putExtra("employeeName", it.nomeFuncionario)
            }
            startActivity(employeeScreen)
        }

        httpClient.listEmployees(idEmpresa).enqueue(object : Callback<List<ListEmployee>> {

            override fun onResponse(
                call: Call<List<ListEmployee>>,
                response: Response<List<ListEmployee>>
            ) {
                when {
                    (response.code() == 200) -> {
                        listEmployee.clear()
//                        for (index in response.body()!!) {
//                            listEmployee.add(index)
//                        }
                        listEmployee.add(response.body()!![0])
                        recyclerView.adapter = adapterClient
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListEmployee>>,
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
