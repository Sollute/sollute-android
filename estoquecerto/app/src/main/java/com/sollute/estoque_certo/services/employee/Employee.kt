package com.sollute.estoque_certo.services.employee

import com.sollute.estoque_certo.models.employee.NewEmployee
import com.sollute.estoque_certo.models.employee.EditEmployee
import retrofit2.Call
import retrofit2.http.*

interface Employee {

    @POST("/funcionarios/criar-funcionario/{idEmpresa}")
    fun postEmployee(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newEmployee: NewEmployee
    ): Call<Void>

    @PUT("/funcionarios/editar-funcionario-cpf/{idEmpresa}/{cpfFuncionario}")
    fun editEmployee(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("cpfFuncionario") cpfFuncionario: String,
        @Body editEmployee: EditEmployee
    ): Call<Void>

    @DELETE("/funcionarios/deletar-funcionario-cpf/{cpfFuncionario}/{idEmpresa}")
    fun deleteEmployee(
        @Path("cpfFuncionario") cpfFuncionario: String,
        @Path("idEmpresa") idEmpresa: Int
    ): Call<Void>

}