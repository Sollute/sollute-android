package com.sollute.estoque_certo.models.company

data class NewCompany(
    val email: String,
    val senha: String,
    val nomeFantasia: String,
    val razaoSocial: String,
    val cnpj: String,
    val qtdProdutosVendidos: Int? = 0,
    val totalProdutosVendidos: Double? = 0.0,
    val autenticado: Boolean? = false,
    val cep: String,
    val uf: String,
    val cidade: String,
    val logradouro: String,
    val pontoReferencia: String,
)
