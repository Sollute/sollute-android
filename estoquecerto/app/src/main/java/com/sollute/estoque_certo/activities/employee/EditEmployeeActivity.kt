package com.sollute.estoque_certo.activities.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.databinding.ActivityEditEmployeeBinding
import com.sollute.estoque_certo.models.employee.EditEmployee
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.employee.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditEmployeeBinding
    private val httpClient: Employee = Rest.getInstance().create(Employee::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditEmployeeBinding.inflate(layoutInflater)

        val idEmpresa = intent.getIntExtra("idEmp", 0)

        setContentView(binding.root)

        binding.btnEditEmployee.setOnClickListener { edit(idEmpresa) }
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

    private fun edit(idEmpresa: Int) {

        val editEmployee = EditEmployee(
            nomeFuncionario = binding.etEmployeeName.text.toString(),
            cpfFuncionario = binding.etEmployeeCPF.text.toString(),
            telefoneFuncionario = binding.etEmployeePhone.text.toString(),
            salario = binding.etEmployeeSalary.text.toString().toDouble()
        )

        httpClient.editEmployee(
            idEmpresa = idEmpresa,
            cpfFuncionario = binding.etEmployeeCPF.text.toString(),
            editEmployee = editEmployee
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Funcionário editado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack(idEmpresa)
                    }
                    (response.code() == 404) -> {
                        Toast.makeText(
                            baseContext,
                            "Esse funcionário não existe.",
                            Toast.LENGTH_LONG
                        ).show()
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
