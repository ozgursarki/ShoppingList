package com.ozgursarki.shoppinglist.presentation.home

import com.ozgursarki.shoppinglist.domain.model.ShoppingItem

data class HomeScreenUIState(
    val shoppingList: ArrayList<Any> = arrayListOf(),
    val hasError: Boolean = false
)
