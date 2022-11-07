package com.sollute.estoque_certo.services.client

import com.sollute.estoque_certo.models.client.NewClient
import com.sollute.estoque_certo.models.product.EditProduct
import com.sollute.estoque_certo.models.product.ListProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Client {

    @POST("/clientes/criar-cliente/{idEmpresa}")
    fun postClient(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newClient: NewClient
    ): Call<Void>

    @POST("/clientes/editar-cliente/{idEmpresa}/{codigo}")
    fun editClient(
        @Path("idEmpresa") idEmpresa: Long,
        @Path("codigo") codigo: String,
        @Body editClient: EditProduct
    ): Call<Void>

    @GET("/clientes/listar-clientes/{idEmpresa}")
    fun listClients(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<ListProduct>

}