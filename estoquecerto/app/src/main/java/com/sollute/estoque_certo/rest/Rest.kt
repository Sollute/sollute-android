package com.sollute.estoque_certo.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    // URL Base para Emulador
    // private val baseURL = "http://localhost:8080"

    // URL Base para Celular fisico
    // private val baseURL = "http://192.168.3.6:8080/"

    private val baseURL = "http://18.234.13.69:8080"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
