package com.ozgursarki.shoppinglist.presentation.detail

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
class DetailViewModel @Inject constructor(
    private val useCase: ShoppingUseCase
) : ViewModel() {

    private val _shoppingList: MutableLiveData<List<ShoppingItem>> = MutableLiveData(listOf())
    val shoppingList: LiveData<List<ShoppingItem>>
        get() = _shoppingList

    fun getShoppingListWithItems(listID: Long) {
        viewModelScope.launch {
            val shoppingListFromDatabase = useCase.getAllShoppingItemsWithoutFlow.invoke(listID)
            _shoppingList.value = shoppingListFromDatabase[0].shoppingItemList
        }
    }
}