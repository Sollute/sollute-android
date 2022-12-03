package com.sollute.estoque_certo.models.extract

data class NewExtract(
    val extractName: String,
    val extractTime: String,
    val extractAmount: Double,
    val extract_type: Int,
    // 1 -> Despesa
    // 2 -> Receita
    // 3 -> Venda de Produtos
)
