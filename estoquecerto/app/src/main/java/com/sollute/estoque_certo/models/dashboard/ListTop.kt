package com.sollute.estoque_certo.models.dashboard

data class ListTop(
    val idProduto: Int,
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
    val qtdVendidos: Int,
    val valorVendidos: Double
)
