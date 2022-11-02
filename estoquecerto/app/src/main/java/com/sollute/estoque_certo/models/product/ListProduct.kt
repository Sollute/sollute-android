package com.sollute.estoque_certo.models.product

data class ListProduct(
    val productName: String,
    val productPrice: Double,
    val productQuantity: Int,
    var vender: Int,
)
