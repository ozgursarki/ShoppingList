package com.ozgursarki.shoppinglist.util

import com.ozgursarki.shoppinglist.domain.model.ShoppingItem

object DummyData {

    fun dummyShoppingItems(): ArrayList<ShoppingItem> {
        val list = arrayListOf<ShoppingItem>()
        repeat(10) {
            list.add(ShoppingItem("Banana", 1))
            list.add(ShoppingItem("Apple", 3))
            list.add(ShoppingItem("Orange", 10))
            list.add(ShoppingItem("Cheery", 9))
            list.add(ShoppingItem("Grape", 2))
            list.add(ShoppingItem("Avocado", 4))
            list.add(ShoppingItem("Lemon", 7))
        }
        return list
    }
}