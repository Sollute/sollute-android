package com.sollute.estoque_certo.models.product.responses

data class EditProductResponse(
    val estoque: Int,
    val estoqueMin: Int,
    val estoqueMax: Int,
    val precoCompra: Double,
    val precoVenda: Double
)
