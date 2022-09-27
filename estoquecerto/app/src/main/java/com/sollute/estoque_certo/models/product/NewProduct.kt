package com.sollute.estoque_certo.models.product

import com.google.gson.annotations.SerializedName

data class NewProduct(
    @SerializedName("codigo")
    val codigo: String,
    val nome: String,
    val marca: String,
    val categoria: String,
    val tamanho: String,
    val peso: Double,
    val precoCompra: Double,
    val precoVenda: Double,
    val estoque: Int,
    val estoqueMin: Int,
    val estoqueMax: Int,
    val qtdVendidos: Int? = 0,
    val valorVendidos: Double? = 0.0
)
