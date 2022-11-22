package com.sollute.estoque_certo.activities.employee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityNewEmployeeBinding
import com.sollute.estoque_certo.models.employee.NewEmployee
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.employee.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewEmployeeActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityNewEmployeeBinding
    private val httpClient: Employee = Rest.getInstance().create(Employee::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEmployeeBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener { goBack() }
        binding.btnFinishEmployee.setOnClickListener { postEmployee(idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack() {
        startActivity(Intent(this, EmployeeActivity::class.java))
    }

    private fun postEmployee(idEmpresa: Int) {

        val newEmployee = NewEmployee(
            nomeFuncionario = binding.etEmployeeName.text.toString(),
            cpfFuncionario = binding.etEmployeeCPF.text.toString(),
            telefoneFuncionario = binding.etEmployeePhone.text.toString(),
            salarioFuncionario = binding.etEmployeeSalary.text.toString().toDouble()
        )

        httpClient.postEmployee(
            idEmpresa = idEmpresa,
            newEmployee = newEmployee
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Funcionário criado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack()
                    }
                    (response.code() == 409) -> {
                        Toast.makeText(
                            baseContext,
                            "Este funcionário já existe",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.etEmployeeName.error
                    }
                    (response.code() == 400) -> {
                        Toast.makeText(
                            baseContext,
                            "ERRO 400",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(
                call: Call<Void>,
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
