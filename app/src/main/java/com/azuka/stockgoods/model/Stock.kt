package com.azuka.stockgoods.model

import androidx.annotation.Keep

@Keep
data class Stock(
    val code: String = "",
    val name: String = "",
    val unit: String = "",
    val amount: Long = 0,
    val deleted: Boolean = false
)