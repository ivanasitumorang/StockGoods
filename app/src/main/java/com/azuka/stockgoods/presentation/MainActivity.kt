package com.azuka.stockgoods.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azuka.stockgoods.constant.AppConstant.INTENT
import com.azuka.stockgoods.constant.StockActionEnum
import com.azuka.stockgoods.constant.StockReferences
import com.azuka.stockgoods.databinding.ActivityMainBinding
import com.azuka.stockgoods.model.Stock
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true)
        setupDataListener()
        setupUIListener()

    }

    private fun setupUIListener() {
        binding.fabAdd.setOnClickListener {
            navigateToAddEditPage(StockActionEnum.Add)
        }
    }

    private fun setupDataListener() {
        val query = database.reference.child(StockReferences.STOCKS)
        val options = FirebaseRecyclerOptions.Builder<Stock>()
            .setQuery(query, Stock::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter = StockListAdapter(options) { stock ->
            navigateToAddEditPage(StockActionEnum.Edit, stock)
        }
        binding.rvStock.adapter = adapter
    }

    private fun navigateToAddEditPage(action: StockActionEnum, stock: Stock? = null) {
        startActivity(
            Intent(this, AddEditActivity::class.java).apply {
                putExtra(INTENT.TAG_ACTIONS, action.code)
                putExtra(INTENT.TAG_STOCK, stock)
            }
        )
    }
}