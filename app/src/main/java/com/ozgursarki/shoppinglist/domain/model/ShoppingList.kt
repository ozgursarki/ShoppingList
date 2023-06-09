package com.ozgursarki.shoppinglist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingList(
    val listID: Long,
    val ratio: Int = 0
): Parcelable
