package com.sollute.estoque_certo.services.extract

import com.sollute.estoque_certo.models.extract.ListExtract
import com.sollute.estoque_certo.models.extract.NewExtract
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Extract {

    @GET("/extratos/listar-extrato/{idEmpresa}")
    fun list(
        @Path("idEmpresa") idEmpresa: Int
    ): Call<List<ListExtract>>

    @POST("/extratos/criar-extrato/{idEmpresa}")
    fun postExtract(
        @Path("idEmpresa") idEmpresa: Int,
        @Body newExtract: NewExtract
    ): Call<Void>

}
