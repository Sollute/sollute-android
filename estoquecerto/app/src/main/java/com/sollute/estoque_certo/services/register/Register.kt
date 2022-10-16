package com.sollute.estoque_certo.services.register

import retrofit2.Call
import com.sollute.estoque_certo.models.company.NewCompany
import retrofit2.http.Body
import retrofit2.http.POST

interface Register {

    @POST("/empresas/criar-empresa")
    fun postEmpresa(
        @Body newCompany: NewCompany
    ): Call<Void>

}
