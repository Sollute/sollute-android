package com.sollute.estoque_certo.models.provider

data class ListProvider(
    val idFornecedor: Int,
    val nomeFornecedor: String,
    val telefoneFornecedor: String,
    val nomeProduto: String,
    val qtd: Int
)