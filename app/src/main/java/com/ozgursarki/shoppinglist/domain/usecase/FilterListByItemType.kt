package com.ozgursarki.shoppinglist.domain.usecase

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.data.local.entities.relations.ShoppingListWithItemsEntity
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.enum.ItemType
import com.ozgursarki.shoppinglist.util.UseCaseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterListByItemType @Inject constructor(
    private val repository: ShoppingRepository
) {

    operator fun invoke(listID: Long): Result<Flow<List<ShoppingListWithItemsEntity>>> {
        val filteredList = arrayListOf<ShoppingListWithItemsEntity>()
        return repository.getListWithItems(listID).onSuccess {
           val breakfastList = UseCaseHelper.filterListByType(
               it,
               filteredList,
               type = ItemType.BREAKFAST
           )

            val breakfast_FruitList = UseCaseHelper.filterListByType(
                it,
                breakfastList,
                type = ItemType.FRUIT
            )
            val break_fruit_snackList = UseCaseHelper.filterListByType(
                it,
                breakfast_FruitList,
                type = ItemType.SNACK
            )
            val b_f_s_vList = UseCaseHelper.filterListByType(
                it,
                break_fruit_snackList,
                type = ItemType.VEGETABLES
            )
            val allList = UseCaseHelper.filterListByType(
                it,
                b_f_s_vList,
                type = ItemType.OTHER
            )
            allList
        }

    }
}