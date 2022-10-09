package com.sollute.estoque_certo.models.product

data class ListProduct(
    val productPhoto: Int,
    val productName: String,
    val productPrice: Double,
    val productQuantity: Int
)
