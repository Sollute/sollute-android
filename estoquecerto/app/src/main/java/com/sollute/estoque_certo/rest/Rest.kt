package com.sollute.estoque_certo.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    // URL Base para Emulador
    private val baseURL = "http://localhost:8080/"

    // URL Base para Celular fisico
    // private val BaseURL = "http://IP_DA_MAQUINA:3000/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
