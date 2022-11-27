package com.sollute.estoque_certo.services.provider

import com.sollute.estoque_certo.models.provider.EditProvider
import com.sollute.estoque_certo.models.provider.ListProvider
import com.sollute.estoque_certo.models.provider.NewProvider
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Provider {

    @POST("/fornecedores/criar-fornecedor/{idEmpresa}")
    fun postProvider(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newProvider: NewProvider
    ): Call<Void>

    @PUT("/fornecedores/editar-fornecedor-telefone/{idEmpresa}/{telefoneFornecedor}")
    fun editProvider(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("telefoneFornecedor") telefoneFornecedor: String,
        @Body editProvider: EditProvider
    ): Call<Void>

    @GET("/fornecedores/listar-fornecedores/{idEmpresa}")
    fun listProvider(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<List<ListProvider>>

    @DELETE("/fornecedores/deletar-fornecedor-telefone/{idEmpresa}/{telefoneFornecedor}")
    fun deleteProvider(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("telefoneFornecedor") telefoneFornecedor: String
    ): Call<Void>

}