package com.azuka.stockgoods.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azuka.stockgoods.R
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Stock) {
        holder.bind(model, clickListener)
    }

    class ViewHolder(private val binding: ItemStockListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock, clickListener: (Stock) -> Unit) {
            binding.run {
                val context = binding.root.context
                tvStockName.text = stock.name
                tvStockCode.text = context.getString(R.string.stock_code, stock.code)
                tvStockAmount.text =
                    context.getString(R.string.stock_amount_unit, stock.amount, stock.unit)
                root.setOnClickListener { clickListener.invoke(stock) }
                ivToggle.setOnClickListener {
                    toggleContentVisibility()
                }
            }
        }

        private fun toggleContentVisibility() {
            binding.run {
                if (clContent.visibility == View.VISIBLE) {
                    clContent.visibility = View.GONE
                    ivToggle.setImageResource(R.drawable.ic_down)
                } else {
                    clContent.visibility = View.VISIBLE
                    ivToggle.setImageResource(R.drawable.ic_up)
                }
            }
        }
    }
}