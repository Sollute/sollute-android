package com.sollute.estoque_certo.services.product

import com.sollute.estoque_certo.models.product.EditProduct
import com.sollute.estoque_certo.models.product.ListProduct
import com.sollute.estoque_certo.models.product.NewProduct
import retrofit2.Call
import retrofit2.http.*

interface Product {

    @POST("/produtos/criar-produto/{idEmpresa}")
    fun postProduct(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newProduct: NewProduct
    ): Call<Void>

    @PUT("/produtos/editar-produto-nome/{idEmpresa}/{nome}")
    fun editProduct(
        @Path("idEmpresa") idEmpresa: Int,
        @Path("nome") nome: String,
        @Body editProduct: EditProduct
    ): Call<Void>

    @DELETE("/produtos/deletar-produto-nome/{nome}/{idEmpresa}")
    fun deleteProduct(
        @Path("nome") nome: String,
        @Path("idEmpresa") idEmpresa: Int
    ): Call<Void>

    @GET("/produtos/listar-produtos-android/{idEmpresa}")
    fun listProducts(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<List<ListProduct>>

    @GET("/produtos/info/{nome}/{fkEmpresa}")
    fun getInfo(
        @Path("nome") nome: String,
        @Path("fkEmpresa") fkEmpresa: Int,
    ): Call<EditProduct>

}
