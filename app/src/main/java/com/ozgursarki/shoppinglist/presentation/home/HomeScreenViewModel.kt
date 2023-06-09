package com.ozgursarki.shoppinglist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingHeader
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val shoppingListUseCases: ShoppingUseCase
) : ViewModel() {

    private var itemSize: Int = 0


    private val _uiState: MutableStateFlow<HomeScreenUIState> =
        MutableStateFlow(HomeScreenUIState())

    val uiState: StateFlow<HomeScreenUIState>
        get() = _uiState.asStateFlow()


    fun getShoppingListWithItems(listID: Long, newItem: () -> Unit) {
        viewModelScope.launch {
            shoppingListUseCases.getShoppingListWithItems.invoke(listID)
                .onSuccess {
                    it.collect { shoppingListItems ->
                        if (shoppingListItems.isNotEmpty() && shoppingListItems[0].shoppingItemEntityList.size != itemSize) {
                            newItem.invoke()
                            delay(1500)
                            itemSize = shoppingListItems[0].shoppingItemEntityList.size
                        }


                        val shoppingItems = shoppingListItems.map { shoppingList ->
                            shoppingList.toShoppingListItems()
                        }
                        val shoppingItemList = try {
                            shoppingItems[0].shoppingItemList
                        } catch (e: Exception) {
                            arrayListOf<ShoppingItem>()
                        }


                        val arrayList = arrayListOf<ShoppingItem>()
                        arrayList.addAll(shoppingItemList)
                        filterShoppingList(arrayList)
                    }
                }
                .onFailure {
                    _uiState.update { homeScreenUIState ->
                        homeScreenUIState.copy(shoppingList = arrayListOf(), hasError = true)
                    }
                }

        }
    }


    fun insertShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            shoppingListUseCases.insertShoppingList.invoke(shoppingList)
        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingListUseCases.updateShoppingItem.invoke(shoppingItem)
        }
    }

    fun deleteShoppingList() {
        viewModelScope.launch {
            shoppingListUseCases.deleteShoppingList.invoke(getListID())
        }
    }

    fun deleteShoppingItemFromDatabase(itemID: Long) {
        viewModelScope.launch {
            shoppingListUseCases.deleteRelatedShoppingItem.invoke(itemID)
        }
    }

    fun deleteShoppingItemsFromDatabase(listID: Long) {
        viewModelScope.launch {
            shoppingListUseCases.deleteRelatedShoppingItems.invoke(listID)
        }
    }

    fun saveListID(listID: Long) {
        shoppingListUseCases.saveListID(listID)
    }

    fun getListID(): Long {
        return shoppingListUseCases.getListID()
    }

    fun saveDelete() {
        shoppingListUseCases.saveDeleteToolTip.saveDeleteToolTip()
    }

    fun getDelete(): Boolean {
        return shoppingListUseCases.getDeleteToolTip.getDeleteToopTip()
    }

    fun saveSave() {
        shoppingListUseCases.saveSaveToolTip.saveSaveToolTip()
    }

    fun getSave(): Boolean {
        return shoppingListUseCases.getSaveToolTip.getSaveToolTip()
    }

    fun saveAdd() {
        shoppingListUseCases.saveAddToolTip.saveAddToolTip()
    }

    fun getAdd(): Boolean {
        return shoppingListUseCases.getAddToolTip.getAddToolTip()
    }

    fun saveHistory() {
        shoppingListUseCases.saveHistoryToolTip.saveHistoryToolTip()
    }

    fun getHistory(): Boolean {
        return shoppingListUseCases.getHistoryToolTip.getHistoryToolTip()
    }

    private fun filterShoppingList(shoppingItemList: ArrayList<ShoppingItem>) {
        viewModelScope.launch {
            val arraylist = arrayListOf<ShoppingItem>()
            arraylist.addAll(shoppingItemList)
            val filteredList = shoppingListUseCases.filterListByItemType.invoke(arraylist)
            _uiState.update {
                it.copy(shoppingList = filteredList, hasError = false)
            }

        }
    }


}
