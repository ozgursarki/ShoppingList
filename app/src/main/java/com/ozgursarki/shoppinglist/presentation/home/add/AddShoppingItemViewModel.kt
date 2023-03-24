package com.ozgursarki.shoppinglist.presentation.home.add

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

    fun insertShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            useCases.insertShoppingItem.invoke(shoppingItem)
        }
    }
}