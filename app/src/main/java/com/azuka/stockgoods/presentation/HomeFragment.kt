package com.azuka.stockgoods.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.azuka.stockgoods.BaseFragment
import com.azuka.stockgoods.R
import com.azuka.stockgoods.databinding.FragmentHomeBinding
import com.azuka.stockgoods.model.Stock


/**
 * Created by ivanaazuka on 06/03/21.
 * Android Engineer
 */

class HomeFragment : BaseFragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.bind(requireView())
    }

    override val viewLayout: Int = R.layout.fragment_home

    override fun onFragmentReady(view: View, savedInstanceState: Bundle?) {
        // dummy list
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

        val adapter = StockListAdapter(stockList) { stock ->
            Toast.makeText(requireContext(), stock.name, Toast.LENGTH_SHORT).show()
        }

        binding.rvStock.adapter = adapter


    }
}