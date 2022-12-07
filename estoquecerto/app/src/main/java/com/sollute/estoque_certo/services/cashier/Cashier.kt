package com.sollute.estoque_certo.services.cashier

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface Cashier {

    @GET("/caixa/pegar-saldo/{idEmpresa}")
    fun getValue(
        @Path("idEmpresa") idEmpresa: Int,
    ): Call<Double>

    @PUT("/caixa/pegar-saldo/{idEmpresa}/{valor}")
    fun addValue(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("valor") valor: Double,
    ): Call<Void>

    @PUT("/caixa/pegar-saldo/{idEmpresa}/{valor}")
    fun removeValue(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("valor") valor: Double,
    ): Call<Void>

}
