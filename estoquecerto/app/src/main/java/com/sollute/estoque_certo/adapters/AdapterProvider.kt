package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.models.provider.ListProvider

class AdapterProvider(
    private val context: Context,
    private val providers: MutableList<ListProvider>,
    private val clickLestener: (ListProvider) -> Unit
) : RecyclerView.Adapter<AdapterProvider.ProviderViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProviderViewHolder {
        return ProviderViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_product_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        holder.providerName.text = providers[position].providerName
        holder.providerPhone.text = providers[position].providerPhone.toString()
        holder.providerProduct.text = providers[position].providerProduct.toString()

        holder.itemView.setOnClickListener {
            clickLestener(providers[position])
        }
    }

    override fun getItemCount(): Int = providers.size

    inner class ProviderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val providerName = itemView.findViewById<TextView>(R.id.tvProviderName)
        val providerPhone = itemView.findViewById<TextView>(R.id.tvProviderPhone)
        val providerProduct = itemView.findViewById<TextView>(R.id.tvProviderProduct)
    }
}