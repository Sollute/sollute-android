package com.sollute.estoque_certo.models.product

data class EditProduct(
    val estoque: Int,
    val estoqueMin: Int,
    val estoqueMax: Int,
    val precoCompra: Double,
    val precoVenda: Double
)
