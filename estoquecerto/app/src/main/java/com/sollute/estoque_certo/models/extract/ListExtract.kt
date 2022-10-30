package com.sollute.estoque_certo.models.extract

data class ListExtract(
    val extractName: String,
    val extractTime: String,
    val extractAmount: Double,
    val extractType: Int
)
