package com.ozgursarki.shoppinglist.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.data.mapper.toShoppingListItems
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import com.ozgursarki.shoppinglist.util.ListHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: ShoppingUseCase
) : ViewModel() {

    private val _shoppingList: MutableStateFlow<List<Any>> = MutableStateFlow(listOf())
    val shoppingList: StateFlow<List<Any>>
        get() = _shoppingList
    private var listID : Long? = null

    fun getShoppingListWithItems(listID: Long) {
        this.listID = listID
        viewModelScope.launch {
            useCase.getShoppingListWithItems.invoke(listID)
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
                    _shoppingList.update {
                        arrayListOf()
                    }
                }

        }
    }


    fun filterShoppingList(shoppingList: ArrayList<ShoppingItem>) {
        viewModelScope.launch {
            val filteredList = useCase.filterListByItemType.invoke(shoppingList)
            _shoppingList.update {
                filteredList
            }
        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            useCase.updateShoppingItem.invoke(shoppingItem)
        }
    }

    fun updateShoppingList(ratio: Int) {
        viewModelScope.launch {
            listID?.let { useCase.updateShoppingList.updateShoppingList(it, ratio) }
        }
    }
}