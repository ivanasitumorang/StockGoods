package com.azuka.stockgoods.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azuka.stockgoods.constant.AppConstant.INTENT
import com.azuka.stockgoods.constant.StockActionEnum
import com.azuka.stockgoods.constant.StockReferences
import com.azuka.stockgoods.databinding.ActivityAddEditBinding
import com.azuka.stockgoods.model.Stock
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class AddEditActivity : AppCompatActivity() {

    private val binding: ActivityAddEditBinding by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }

    private val extraAction: Int by lazy {
        intent.getIntExtra(INTENT.TAG_ACTIONS, StockActionEnum.Add.code)
    }

    private val extraStock: Stock? by lazy {
        intent.getParcelableExtra(INTENT.TAG_STOCK)
    }

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupContent()
    }


    private fun setupContent() {
        when (extraAction) {
            StockActionEnum.Add.code -> {
            }

            StockActionEnum.Edit.code -> {
                binding.run {
                    extraStock?.let {
                        with(it) {
                            etStockCode.setText(code)
                            etStockName.setText(name)
                            etStockAmount.setText("$amount")
                            etStockUnit.setText(unit)
                        }
                    }
                    btnSave.setOnClickListener {
                        val stock = Stock(
                            code = etStockCode.text.toString(),
                            name = etStockName.text.toString(),
                            amount = etStockAmount.text.toString().toLong(),
                            unit = etStockUnit.text.toString()
                        )
                        saveData(stock)
                    }
                }
            }
        }
    }

    private fun saveData(stock: Stock) {
        // My top posts by number of stars
        val stocks = database.child(StockReferences.STOCKS)
        stocks.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val stockValue = postSnapshot.getValue<Stock>()
                    stockValue?.let {
                        if (it.code == stock.code) {
                            stocks.child(postSnapshot.key!!).setValue(stock)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //
            }
        })
        finish()
    }
}