package com.azuka.stockgoods.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azuka.stockgoods.R
import com.azuka.stockgoods.constant.AppConstant
import com.azuka.stockgoods.constant.AppConstant.INTENT
import com.azuka.stockgoods.constant.StockActionEnum
import com.azuka.stockgoods.databinding.ActivityAddEditBinding

class AddEditActivity : AppCompatActivity() {

    private val binding: ActivityAddEditBinding by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }

    private val extraAction: Int by lazy {
        intent.getIntExtra(INTENT.TAG_ACTIONS, StockActionEnum.Add.code)
    }

    private val extraStockCode: String? by lazy {
        intent.getStringExtra(INTENT.TAG_STOCK_CODE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupContent()
    }

    private fun setupContent() {
        when (extraAction) {
            StockActionEnum.Add.code -> {
                binding.tvDummy.text = StockActionEnum.Add.toString()
            }

            StockActionEnum.Edit.code -> {
                binding.tvDummy.text = extraStockCode
            }
        }
    }
}