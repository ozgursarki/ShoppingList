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
            deleteRelatedShoppingItems = DeleteRelatedShoppingItems(repository),
            getAddToolTip = GetAddToolTip(repository),
            getDeleteToolTip = GetDeleteToolTip(repository),
            getHistoryToolTip = GetHistoryToolTip(repository),
            getSaveToolTip = GetSaveToolTip(repository),
            saveAddToolTip = SaveAddToolTip(repository),
            saveDeleteToolTip = SaveDeleteToolTip(repository),
            saveHistoryToolTip = SaveHistoryToolTip(repository),
            saveSaveToolTip = SaveSaveToolTip(repository),
            updateShoppingList = UpdateShoppingList(repository),
            getShoppingListByDate = GetShoppingListByDate(repository)
        )
    }
}