package com.ozgursarki.shoppinglist.domain.di

import com.ozgursarki.shoppinglist.data.local.ShoppingRepository
import com.ozgursarki.shoppinglist.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideShoppingUseCases(repository: ShoppingRepository): ShoppingUseCase {
        return ShoppingUseCase(
            insertShoppingItem = InsertShoppingItem(repository),
            insertShoppingList = InsertShoppingList(repository),
            getShoppingListWithItems = GetShoppingListWithItems(repository),
            updateShoppingItem = UpdateShoppingItem(repository),
            deleteRelatedShoppingItem = DeleteRelatedShoppingItem(repository),
            getAllShoppingList = GetAllShoppingList(repository),
            getAllShoppingItemsWithoutFlow = GetAllShoppingItemsWithoutFlow(repository),
            deleteShoppingList = DeleteShoppingList(repository),
            getListID = GetListID(repository),
            saveListID = SaveListID(repository),
            filterListByItemType = FilterListByItemType(),
            deleteRelatedShoppingItems = DeleteRelatedShoppingItems(repository)
        )
    }
}