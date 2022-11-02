package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.databinding.ActivityProductItemBinding
import com.sollute.estoque_certo.models.product.ListProduct

class AdapterProduct(
    private val context: Context,
    private val products: MutableList<ListProduct>,
    private val clickLestener: (ListProduct) -> Unit
) : RecyclerView.Adapter<AdapterProduct.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_product_item,
                parent,
                false
            )
        )
    }

    private fun diminuirProduto(produto: ListProduct, itemView: TextView) {
        if (produto.vender > 0) {
            produto.vender -= 1
            itemView.text = produto.vender.toString()
        }
    }

    private fun aumentarProduto(produto: ListProduct, itemView: TextView) {
        if (produto.productQuantity <= produto.vender + 1) {
            produto.vender += 1
            itemView.text = produto.vender.toString()
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
        holder.itemView.setOnClickListener {
            clickLestener(products[position])
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: ListProduct) {
            itemView.findViewById<TextView>(R.id.tvExtractName).text = product.productName
            itemView.findViewById<TextView>(R.id.tvProductPrice).text =
                product.productPrice.toString()
            itemView.findViewById<TextView>(R.id.quantityItem).text =
                product.productQuantity.toString()

            itemView.findViewById<Button>(R.id.btnMinus).setOnClickListener {
                diminuirProduto(product, itemView.findViewById<TextView>(R.id.quantityItem))
            }
            itemView.findViewById<Button>(R.id.btnPlus).setOnClickListener {
                aumentarProduto(product, itemView.findViewById<TextView>(R.id.quantityItem))
            }
        }
    }
}
