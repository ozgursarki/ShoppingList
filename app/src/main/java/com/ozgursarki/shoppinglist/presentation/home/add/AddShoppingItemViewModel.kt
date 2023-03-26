package com.ozgursarki.shoppinglist.presentation.home.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddShoppingItemViewModel @Inject constructor(
    private val useCases: ShoppingUseCase
): ViewModel() {

    private val _isItemExist: MutableLiveData<Pair<Boolean, ShoppingItem?>> = MutableLiveData()
    val isItemExist: LiveData<Pair<Boolean, ShoppingItem?>>
        get() = _isItemExist

    fun insertShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            useCases.insertShoppingItem.invoke(shoppingItem)
        }
    }

    fun getShoppingItemsByListID(listID: Long, itemName: String) {
        val itemList: ArrayList<ShoppingItem> = arrayListOf()
        viewModelScope.launch {
            val list = useCases.getAllShoppingItemsWithoutFlow.invoke(listID)
            itemList.addAll(list[0].shoppingItemList)
            val newList = itemList.filter {
                it.listID == listID && it.name == itemName
            }
            if (newList.isEmpty()) {
                _isItemExist.value = Pair(true, null)
            } else {
                _isItemExist.value = Pair(false, newList[0])
            }

        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            useCases.updateShoppingItem.invoke(shoppingItem)
        }
    }

}