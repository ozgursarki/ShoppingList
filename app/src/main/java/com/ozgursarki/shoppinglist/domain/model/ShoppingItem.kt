package com.ozgursarki.shoppinglist.domain.model

import java.util.Calendar


data class ShoppingItem(
    val itemID: Long = 0,
    val name: String,
    var count: Int,
    val listID: Long,
    val date: String
)
