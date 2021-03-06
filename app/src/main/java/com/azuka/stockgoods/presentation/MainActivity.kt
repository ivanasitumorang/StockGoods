package com.azuka.stockgoods.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.azuka.stockgoods.constant.StockActionEnum
import com.azuka.stockgoods.constant.StockReferences
import com.azuka.stockgoods.databinding.ActivityMainBinding
import com.azuka.stockgoods.model.Stock
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupDataListener()
        setupUIListener()

    }

    private fun setupUIListener() {
        binding.fabAdd.setOnClickListener {
            val toAdd = Intent(this, AddEditActivity::class.java).apply {
                putExtra("action", StockActionEnum.Add.toString())
            }
            startActivity(toAdd)
        }
    }

    private fun setupDataListener() {
        val query = database.child(StockReferences.STOCKS)
        val options = FirebaseRecyclerOptions.Builder<Stock>()
            .setQuery(query, Stock::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter = StockListAdapter(options) { stock ->
            Toast.makeText(this, stock.name, Toast.LENGTH_SHORT).show()
        }
        binding.rvStock.adapter = adapter
    }
}