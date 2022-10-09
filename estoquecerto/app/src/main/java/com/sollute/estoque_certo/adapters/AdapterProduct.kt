package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
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

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productPhoto.setImageResource(products[position].productPhoto)
        holder.productName.text = products[position].productName
        holder.productPrice.text = products[position].productPrice.toString()
        holder.productQuantity.text = products[position].productQuantity.toString()

        holder.itemView.setOnClickListener {
            clickLestener(products[position])
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productPhoto = itemView.findViewById<ImageView>(R.id.ivProductPhoto)
        val productName = itemView.findViewById<TextView>(R.id.tvProductNameItem)
        val productPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)
        val productQuantity = itemView.findViewById<TextView>(R.id.tvProductQuantity)
    }
}
