package com.azuka.stockgoods.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azuka.stockgoods.databinding.ItemStockListBinding
import com.azuka.stockgoods.model.Stock


/**
 * Created by ivanaazuka on 06/03/21.
 * Android Engineer
 */

class StockListAdapter(private val data: List<Stock>, private val clickListener: (Stock) -> Unit) :
    RecyclerView.Adapter<StockListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemStockListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(private val binding: ItemStockListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock, clickListener: (Stock) -> Unit) {
            binding.run {
                tvStockName.text = stock.name
                tvStockCode.text = stock.code
                tvStockAmount.text = ("${stock.amount} ${stock.unit}")
                root.setOnClickListener { clickListener.invoke(stock) }
            }
        }
    }
}