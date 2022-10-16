package com.sollute.estoque_certo.services.auth

import com.sollute.estoque_certo.models.login.Login
import com.sollute.estoque_certo.models.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthLogin {

    @POST("/empresas/autenticacao")
    fun login(
        @Body body: Login
    ): Call<LoginResponse>

}