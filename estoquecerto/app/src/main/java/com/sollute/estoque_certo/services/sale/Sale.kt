package com.sollute.estoque_certo.services.sale

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Sale {

    @POST("/carrinhos/adicionar-carrinho-android/{idEmpresa}/{productName}/{productQuantity}")
    fun addCart(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("productName") productName: String,
        @Path("productQuantity") productQuantity: Int,
    ): Call<Void>

    @PUT("carrinhos/vender-produtos-carrinho/{idEmpresa}")
    fun sellCart(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<Void>

}
