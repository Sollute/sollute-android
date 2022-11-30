package com.sollute.estoque_certo.services.dashboard

import com.sollute.estoque_certo.models.dashboard.ListTop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TopCinco {

    @GET("/produtos/listar-produtos-ordem-maior/{idEmpresa}")
    fun listProducts(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<List<ListTop>>
}