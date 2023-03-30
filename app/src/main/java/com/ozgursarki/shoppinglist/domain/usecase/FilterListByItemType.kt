package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingHeader
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.enum.ItemType
import com.ozgursarki.shoppinglist.util.UseCaseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterListByItemType @Inject constructor() {

    operator fun invoke(shoppingList: ArrayList<ShoppingItem>): ArrayList<Any> {
        val filteredList = arrayListOf<Any>()

        filteredList.add(ShoppingHeader("BREAKFAST"))
        val breakfastList = UseCaseHelper.filterListByType(
            shoppingList,
            filteredList,
            type = ItemType.BREAKFAST
        )

        filteredList.add(ShoppingHeader("Fruit"))
        val breakfast_FruitList = UseCaseHelper.filterListByType(
            shoppingList,
            breakfastList,
            type = ItemType.FRUIT
        )

        filteredList.add(ShoppingHeader("snack"))
        val break_fruit_snackList = UseCaseHelper.filterListByType(
            shoppingList,
            breakfast_FruitList,
            type = ItemType.SNACK
        )

        filteredList.add(ShoppingHeader("vegetables"))
        val b_f_s_vList = UseCaseHelper.filterListByType(
            shoppingList,
            break_fruit_snackList,
            type = ItemType.VEGETABLES
        )

        filteredList.add(ShoppingHeader("other"))
        val allList = UseCaseHelper.filterListByType(
            shoppingList,
            b_f_s_vList,
            type = ItemType.OTHER
        )
        return allList


    }
}