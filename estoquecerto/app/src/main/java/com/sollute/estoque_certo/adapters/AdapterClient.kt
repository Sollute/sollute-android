package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.models.client.ListClient

class AdapterClient (
    private val context: Context,
    private val clients: MutableList<ListClient>,
    private val clickListener: (ListClient) -> Unit
) : RecyclerView.Adapter<AdapterClient.ClientViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientViewHolder {
        return ClientViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.activity_client_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.clientName.text = clients[position].nomeCliente
        holder.clientPhone.text = clients[position].telefoneCliente

        holder.itemView.setOnClickListener {
            clickListener(clients[position])
        }
    }

    override fun getItemCount(): Int = clients.size

    inner class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clientName = itemView.findViewById<TextView>(R.id.tvClientName)
        val clientPhone = itemView.findViewById<TextView>(R.id.tvClientPhone)
    }
}
