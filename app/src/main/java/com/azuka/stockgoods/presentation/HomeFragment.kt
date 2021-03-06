package com.azuka.stockgoods.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.azuka.stockgoods.BaseFragment
import com.azuka.stockgoods.R
import com.azuka.stockgoods.databinding.FragmentHomeBinding
import com.azuka.stockgoods.model.Stock
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


/**
 * Created by ivanaazuka on 06/03/21.
 * Android Engineer
 */

class HomeFragment : BaseFragment() {

    companion object {
        private const val TAG = "Hasil"
    }

    private lateinit var adapter: StockListAdapter

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.bind(requireView())
    }

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    override val viewLayout: Int = R.layout.fragment_home

    override fun onFragmentReady(view: View, savedInstanceState: Bundle?) {

        val query = database.child("stocks")
        val options = FirebaseRecyclerOptions.Builder<Stock>()
            .setQuery(query, Stock::class.java)
            .setLifecycleOwner(this)
            .build()

        adapter = StockListAdapter(options) { stock ->
            Toast.makeText(requireContext(), stock.name, Toast.LENGTH_SHORT).show()
        }

        binding.rvStock.adapter = adapter

        setDummyData()
    }

    private fun setDummyData() {
        val stockList = listOf(
            Stock(
                code = "A123",
                name = "Paper 1",
                amount = 100,
                unit = "Lembar",
                deleted = false
            ),
            Stock(
                code = "B123",
                name = "Paper 2",
                amount = 100,
                unit = "Lembar",
                deleted = false
            ),
            Stock(
                code = "C123",
                name = "Paper 3",
                amount = 100,
                unit = "Lembar",
                deleted = false
            ),
            Stock(
                code = "D123",
                name = "Paper 4",
                amount = 100,
                unit = "Lembar",
                deleted = false
            ),
        )

        // save data to db with child ("stock") -> create if not exists
        database.child("stocks").setValue(stockList)
    }
}