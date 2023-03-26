package com.ozgursarki.shoppinglist.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCase: ShoppingUseCase
) : ViewModel() {

    private val _shoppingList: MutableLiveData<List<ShoppingList>> = MutableLiveData(listOf())
    val shoppingList: LiveData<List<ShoppingList>>
        get() = _shoppingList


    fun getAllShoppingList() {
        viewModelScope.launch {
            val shoppingListFromDatabase = useCase.getAllShoppingList.invoke()
            _shoppingList.value = shoppingListFromDatabase
        }
    }

    fun removeListFromDatabase(listID: Long) {
        viewModelScope.launch {
            useCase.deleteRelatedShoppingItems.invoke(listID)
        }
    }

    fun removeAllRelatedItems(listID: Long) {
        viewModelScope.launch {
            useCase.deleteRelatedShoppingItems.invoke(listID)
        }
    }
}