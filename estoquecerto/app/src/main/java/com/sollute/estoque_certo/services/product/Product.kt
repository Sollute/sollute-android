package com.sollute.estoque_certo.services.product

import com.sollute.estoque_certo.models.product.NewProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface Product {

    @POST("produtos/criar-produto/{idEmpresa}")
    fun postProduct(
        @Path("idEmpresa") idEmpresa: Long,
        @Body newProduct: NewProduct
    ): Call<Void>

}
