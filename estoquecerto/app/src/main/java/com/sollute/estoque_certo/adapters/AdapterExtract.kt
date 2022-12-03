package com.sollute.estoque_certo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.sollute.estoque_certo.R
import com.sollute.estoque_certo.models.extract.ListExtract

class AdapterExtract(
    private val context: Context,
    private val extracts: MutableList<ListExtract>
) : RecyclerView.Adapter<AdapterExtract.ExtractViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ExtractViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.activity_extract_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = extracts.size

    override fun onBindViewHolder(holder: ExtractViewHolder, position: Int) {
        holder.extractName.text = extracts[position].extractName
        holder.extractTime.text = extracts[position].extractTime
        holder.extractAmount.text = extracts[position].extractAmount.toString()
        when (extracts[position].extractType) {
            1 -> holder.extractAmount.setTextColor(AppCompatResources.getColorStateList(this, R.color.red))
            2 -> holder.extractAmount.setTextColor(AppCompatResources.getColorStateList(this, R.color.green))
            3 -> holder.extractAmount.setTextColor(AppCompatResources.getColorStateList(this, R.color.blue))
        }
    }

    inner class ExtractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val extractName = itemView.findViewById<TextView>(R.id.tvExtractName)
        val extractTime = itemView.findViewById<TextView>(R.id.tvExtractTime)
        val extractAmount = itemView.findViewById<TextView>(R.id.tvExtractAmount)
    }

}
