package com.sollute.estoque_certo.activities.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.databinding.ActivityNewEmployeeBinding
import com.sollute.estoque_certo.models.employee.NewEmployee
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.employee.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewEmployeeBinding
    private val httpClient: Employee = Rest.getInstance().create(Employee::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEmployeeBinding.inflate(layoutInflater)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        setContentView(binding.root)

        binding.btnFinishEmployee.setOnClickListener { postEmployee(idEmpresa) }
        binding.btnBack.setOnClickListener { goBack(idEmpresa) }
    }

    private fun goBack(idEmpresa: Int) {
        val productScreen = Intent(
            this,
            EmployeeActivity::class.java
        )
        productScreen.putExtra("idEmp", idEmpresa)
        startActivity(productScreen)
    }

    private fun postEmployee(idEmpresa: Int) {

        val newEmployee = NewEmployee(
            nomeFuncionario = binding.etEmployeeName.text.toString(),
            cpfFuncionario = binding.etEmployeeCPF.text.toString(),
            telefoneFuncionario = binding.etEmployeePhone.text.toString(),
            salario = binding.etEmployeeSalary.text.toString().toDouble()
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
                        goBack(idEmpresa)
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
                print("not ok")
            }

        })

    }
}
