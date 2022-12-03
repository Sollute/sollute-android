package com.sollute.estoque_certo.activities.employee

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sollute.estoque_certo.activities.menu.DrawerBaseActivity
import com.sollute.estoque_certo.databinding.ActivityEditEmployeeBinding
import com.sollute.estoque_certo.models.employee.EditEmployee
import com.sollute.estoque_certo.rest.Rest
import com.sollute.estoque_certo.services.employee.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditEmployeeActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityEditEmployeeBinding
    private val httpClient: Employee = Rest.getInstance().create(Employee::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditEmployeeBinding.inflate(layoutInflater)

        val idEmpresa: Int = getPreferences(MODE_PRIVATE).getInt("idEmpresa", 1)
        val employeeName = binding.etEmployeeName.text.toString()

        setContentView(binding.root)

        binding.btnBack.setOnClickListener { goBack() }
        binding.btnEditEmployee.setOnClickListener { edit(idEmpresa) }
        binding.btnDeleteEmployee.setOnClickListener { confirmation(employeeName, idEmpresa) }
        binding.tvMenuHamburguer.setOnClickListener { super.drawerLayout.open() }
    }

    private fun goBack() = startActivity(Intent(this, EmployeeActivity::class.java))

    private fun confirmation(
        employeeName: String,
        idEmpresa: Int
    ) = AlertDialog.Builder(this)
        .also {
            it.setTitle("Atenção")
            it.setMessage("Tem certeza que deseja excluir o funcionário $employeeName ?")
            it.setPositiveButton("Excluir") { _, _ -> delete(idEmpresa) }
            it.setNegativeButton("Cancelar") { option, _ ->
                option.cancel()
                Toast.makeText(
                    this,
                    "Ação cancelada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.create().show()

    private fun delete(
        idEmpresa: Int
    ) {

        httpClient.deleteEmployee(
            cpfFuncionario = binding.etEmployeeCPF.text.toString(),
            idEmpresa = idEmpresa
        ).enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                when {
                    (response.isSuccessful) -> {
                        Toast.makeText(
                            baseContext,
                            "Funcionário excluído com sucesso",
                            Toast.LENGTH_LONG
                        ).show()
                        goBack()
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

    private fun edit(
        idEmpresa: Int
    ) {

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
                        goBack()
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
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        })

    }

}
