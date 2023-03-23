package com.ozgursarki.shoppinglist.presentation.home

import com.ozgursarki.shoppinglist.domain.ShoppingItem

data class HomeScreenUIState(
    val shoppingList: ArrayList<ShoppingItem> = arrayListOf()
)
