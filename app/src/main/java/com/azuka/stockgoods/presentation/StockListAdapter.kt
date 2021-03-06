package com.azuka.stockgoods.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azuka.stockgoods.databinding.ItemStockListBinding
import com.azuka.stockgoods.model.Stock
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


/**
 * Created by ivanaazuka on 06/03/21.
 * Android Engineer
 */

class StockListAdapter(
    options: FirebaseRecyclerOptions<Stock>,
    private val clickListener: (Stock) -> Unit
) :
    FirebaseRecyclerAdapter<Stock, StockListAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemStockListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Stock) {
        holder.bind(model, clickListener)
    }
}