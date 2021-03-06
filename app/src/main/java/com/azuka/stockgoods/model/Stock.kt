package com.azuka.stockgoods.model


data class Stock(
    val code: String,
    val name: String,
    val unit: String,
    val amount: Long,
    val deleted: Boolean = false
)