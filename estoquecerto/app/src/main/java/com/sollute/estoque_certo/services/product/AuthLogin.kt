package com.sollute.estoque_certo.services.product

import com.sollute.estoque_certo.models.login.Login
import com.sollute.estoque_certo.models.login.LoginResponse
import retrofit2.Call
import retrofit2.http.POST

interface AuthLogin {

    @POST("/empresas/autenticacao")
    fun login(body: Login): Call<LoginResponse>

}