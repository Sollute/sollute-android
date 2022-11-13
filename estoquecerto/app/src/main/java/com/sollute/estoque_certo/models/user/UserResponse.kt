package com.sollute.estoque_certo.models.user

data class UserResponse(
    val nomeFantasia: String,
    val razaoSocial: String,
    val cnpj: String,
    val email: String,
    val logradouro: String,
    val cidade: String
)
