package com.sollute.estoque_certo.services.user

import com.sollute.estoque_certo.models.user.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface User {

    @GET("/empresas/informacoes/{fkEmpresa}")
    fun getInfo(
        @Path("fkEmpresa") fkEmpresa: Int,
    ): Call<UserResponse>
}
