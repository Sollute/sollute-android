package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.models.employee.ListEmployee

class AdapterEmployee(
    private val context: Context,
    private val employees: MutableList<ListEmployee>,
    private val clickListener: (ListEmployee) -> Unit
) : RecyclerView.Adapter<AdapterEmployee.EmployeeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = EmployeeViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.activity_employee_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.employeeNome.text = employees[position].nomeFuncionario
        holder.employeeCPF.text = employees[position].cpfFuncionario
        holder.employeePhone.text = employees[position].telefoneFuncionario
        holder.employeeSalary.text = employees[position].salarioFuncionario.toString()

        holder.itemView.setOnClickListener {
            clickListener(employees[position])
        }
    }

    override fun getItemCount(): Int = employees.size

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeNome: TextView = itemView.findViewById(R.id.tvEmployeeName)
        val employeeCPF: TextView = itemView.findViewById(R.id.tvEmployeeCPF)
        val employeePhone: TextView = itemView.findViewById(R.id.tvEmployeePhone)
        val employeeSalary: TextView = itemView.findViewById(R.id.tvEmployeeSalary)
    }
}
