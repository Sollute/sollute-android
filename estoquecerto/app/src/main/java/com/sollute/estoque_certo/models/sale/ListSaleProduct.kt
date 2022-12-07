package com.sollute.estoque_certo.models.sale

data class ListSaleProduct(
    val idCarrinho: Int,
    val fkEmpresa: fkEmpresa,
    val fkProduto: fkProduto,
    val qtdVenda: Int,
    val valorVenda: Double
)

data class fkEmpresa(
    val idEmpresa: Int,
    val email: String,
    val senha: String,
    val nomeFantasia: String,
    val razaoSocial: String,
    val cnpj: String,
    val qtdProdutosVendidos: Int,
    val totalProdutosVendidos: Int,
    val autenticado: Boolean,
    val cep: String,
    val uf: String,
    val cidade: String,
    val logradouro: String,
    val pontoReferencia: String,
)

data class fkProduto(
    val idProduto: Int,
    val codigo: String,
    val nome: String,
    val marca: String,
    val categoria: String,
    val tamanho: String,
    val peso: Double,
    val precoCompra: Double,
    val precoVenda: Double,
    val estoque: Int,
    val estoqueMin: Int,
    val estoqueMax: Int,
    val qtdVendidos: Int,
    val valorVendidos: Double,
)
