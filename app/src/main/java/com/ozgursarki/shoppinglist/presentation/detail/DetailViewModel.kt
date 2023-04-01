package com.ozgursarki.shoppinglist.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import com.ozgursarki.shoppinglist.util.ListHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: ShoppingUseCase
) : ViewModel() {

    private val _shoppingList: MutableLiveData<List<Any>> = MutableLiveData(listOf())
    val shoppingList: LiveData<List<Any>>
        get() = _shoppingList

    fun getShoppingListWithItems(listID: Long) {
        viewModelScope.launch {
            val shoppingListFromDatabase = useCase.getAllShoppingItemsWithoutFlow.invoke(listID)
            filterShoppingList(ListHelper.convertToList(shoppingListFromDatabase[0].shoppingItemList))
        }
    }

    fun filterShoppingList(shoppingList: ArrayList<ShoppingItem>) {
        viewModelScope.launch {
            val filteredList = useCase.filterListByItemType.invoke(shoppingList)
            _shoppingList.value = filteredList
        }
    }
}