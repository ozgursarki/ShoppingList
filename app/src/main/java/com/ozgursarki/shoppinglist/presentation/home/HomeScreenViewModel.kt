package com.ozgursarki.shoppinglist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingHeader
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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


    private val _uiState: MutableStateFlow<HomeScreenUIState> =
        MutableStateFlow(HomeScreenUIState())

    val uiState: StateFlow<HomeScreenUIState>
        get() = _uiState.asStateFlow()


    fun getShoppingListWithItems(listID: Long) {
        viewModelScope.launch {
            shoppingListUseCases.getShoppingListWithItems.invoke(listID)
                .onSuccess {
                    it.collect { shoppingListItems ->

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

    fun deleteShoppingItemsFromDatabase(itemID: Long) {
        viewModelScope.launch {
            shoppingListUseCases.deleteRelatedShoppingItems.invoke(itemID)
        }
    }

    fun saveListID(listID: Long) {
        shoppingListUseCases.saveListID(listID)
    }

    fun getListID(): Long {
        return shoppingListUseCases.getListID()
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
    fun removeRelatedItems(shoppingHeader: ShoppingHeader) {

        val arrayList = arrayListOf<Any>()
        _uiState.value.shoppingList.forEach{
            if(it is ShoppingItem && it.type.equals(shoppingHeader.title, ignoreCase = true)) {
            }else {
                arrayList.add(it)
            }
        }
        _uiState.update {
            it.copy(arrayList,false)
        }
    }

}
