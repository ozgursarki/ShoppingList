package com.ozgursarki.shoppinglist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import com.ozgursarki.shoppinglist.util.DummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val shoppingListUseCases: ShoppingUseCase
) : ViewModel() {


    var listID: Long = 0

    private val _uiState: MutableStateFlow<HomeScreenUIState> =
        MutableStateFlow(HomeScreenUIState())

    val uiState: StateFlow<HomeScreenUIState>
        get() = _uiState

    fun getShoppingListWithItems(listID: Long) {
        viewModelScope.launch {
            val shoppingListWithItems = shoppingListUseCases.getShoppingListWithItems.invoke(listID)
            shoppingListWithItems.collect { shoppingListItems ->
                _uiState.update { homeScreenUIState ->
                    val shoppingItems = shoppingListItems.map {
                        it.toShoppingListItems()
                    }
                    try {
                        homeScreenUIState.copy(shoppingList = shoppingItems[0].shoppingItemList)
                    } catch (e: Exception) {
                        HomeScreenUIState(arrayListOf())
                    }
                }
            }
        }
    }



    fun insertShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            shoppingListUseCases.insertShoppingList.invoke(shoppingList)
        }
    }

    fun isListCreated(): Boolean {
        return listID != 0L
    }
}
