package com.sollute.estoque_certo.models.product

import android.os.Parcel
import android.os.Parcelable

data class NewProduct(
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
    val qtdVendidos: Int? = 0,
    val valorVendidos: Double? = 0.0
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(codigo)
        parcel.writeString(nome)
        parcel.writeString(marca)
        parcel.writeString(categoria)
        parcel.writeString(tamanho)
        parcel.writeDouble(peso)
        parcel.writeDouble(precoCompra)
        parcel.writeDouble(precoVenda)
        parcel.writeInt(estoque)
        parcel.writeInt(estoqueMin)
        parcel.writeInt(estoqueMax)
        parcel.writeValue(qtdVendidos)
        parcel.writeValue(valorVendidos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewProduct> {
        override fun createFromParcel(parcel: Parcel): NewProduct {
            return NewProduct(parcel)
        }

        override fun newArray(size: Int): Array<NewProduct?> {
            return arrayOfNulls(size)
        }
    }
}
