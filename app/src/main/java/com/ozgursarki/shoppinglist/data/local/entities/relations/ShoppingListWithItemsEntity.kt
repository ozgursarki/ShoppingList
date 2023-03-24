package com.ozgursarki.shoppinglist.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingItemEntity
import com.ozgursarki.shoppinglist.data.local.entities.ShoppingListEntity

data class ShoppingListWithItemsEntity(
    @Embedded val shoppingListEntity: ShoppingListEntity,
    @Relation(
        parentColumn = "listID",
        entityColumn = "listID",
    )
    val shoppingItemEntityList: List<ShoppingItemEntity>
)
