package com.sollute.estoque_certo.services.provider

import com.sollute.estoque_certo.models.client.EditClient
import com.sollute.estoque_certo.models.client.NewClient
import com.sollute.estoque_certo.models.product.ListProduct
import com.sollute.estoque_certo.models.provider.ListProvider
import com.sollute.estoque_certo.models.provider.NewProvider
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Provider {

    @POST("/fornecedores/criar-fornecedor/{idEmpresa}")
    fun postProvider(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newProvider: NewProvider
    ): Call<Void>

    @POST("/fornecedores/editar-fornecedor/{idEmpresa}/{idFornecedor}")
    fun editProvider(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("idFornecedor") idFornecedor: Long,
        @Body editClient: EditClient
    ): Call<Void>

    @GET("/fornecedores/listar-fornecedores/{idEmpresa}")
    fun listProvider(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<List<ListProvider>>

}