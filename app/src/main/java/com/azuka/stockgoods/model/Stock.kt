package com.azuka.stockgoods.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stock(
    val code: String = "",
    val name: String = "",
    val unit: String = "",
    val amount: Long = 0,
    val deleted: Boolean = false
) : Parcelable