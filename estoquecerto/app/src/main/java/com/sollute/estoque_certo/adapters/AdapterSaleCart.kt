package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.models.sale.ListSaleProduct

class AdapterSaleCart(
    private val context: Context,
    private val products: MutableList<ListSaleProduct>,
) : RecyclerView.Adapter<AdapterSaleCart.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ProductViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.activity_product_sale_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productName.text = products[position].fkProduto.nome
        holder.productPrice.text = products[position].valorVenda.toString()
        holder.productQuantity.text = products[position].qtdVenda.toString()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvProductSaleName)
        val productPrice: TextView = itemView.findViewById(R.id.tvProductSalePrice)
        val productQuantity: TextView = itemView.findViewById(R.id.tvProductSaleAmount)
    }
}
