package com.sollute.estoque_certo.models.provider

data class NewProvider(
    val nomeFornecedor: String,
    val telefoneFornecedor: String,
    val nomeProduto: String,
    val qtd: Int
)
