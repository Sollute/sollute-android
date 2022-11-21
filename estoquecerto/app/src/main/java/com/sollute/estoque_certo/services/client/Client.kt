package com.sollute.estoque_certo.services.client

import com.sollute.estoque_certo.models.client.EditClient
import com.sollute.estoque_certo.models.client.ListClient
import com.sollute.estoque_certo.models.client.NewClient
import com.sollute.estoque_certo.models.product.ListProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Client {

    @POST("/clientes/adicionar-cliente/{idEmpresa}")
    fun postClient(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newClient: NewClient
    ): Call<Void>

    @POST("/clientes/editar-cliente/{idEmpresa}/{idCliente}")
    fun editClient(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("idCliente") idCliente: Int,
        @Body editClient: EditClient
    ): Call<Void>

    @GET("/clientes/listar-clientes/{idEmpresa}")
    fun listClients(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<List<ListClient>>

}