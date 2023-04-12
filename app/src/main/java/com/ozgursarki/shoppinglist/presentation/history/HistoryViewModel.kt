package com.ozgursarki.shoppinglist.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgursarki.shoppinglist.domain.usecase.ShoppingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCase: ShoppingUseCase
) : ViewModel() {

    private val _historyUIState: MutableLiveData<HistoryUIState> = MutableLiveData(HistoryUIState())
    val historyUIState: LiveData<HistoryUIState>
        get() = _historyUIState


    fun removeAllRelatedItems(listID: Long) {
        viewModelScope.launch {
            useCase.deleteRelatedShoppingItem.invoke(listID)
        }
    }

    fun deleteShoppingList(listID: Long) {
        viewModelScope.launch {
            useCase.deleteShoppingList.invoke(listID)
        }
    }

    fun deleteShoppingItemsFromDatabase() {
        viewModelScope.launch {
            useCase.deleteRelatedShoppingItems.invoke(getListID())
        }
    }

    fun getListID(): Long {
        return useCase.getListID.invoke()
    }

    fun getListByDate() {
        viewModelScope.launch {
            historyUIState.value?.date?.let {
                val resultList = useCase.getShoppingListByDate.invoke(it.timeInMillis)
                _historyUIState.value = historyUIState.value!!.copy(list = resultList)
            }
        }

    }

    fun changeDate(isForward: Boolean) {
        if (isForward) {
            val calendar = _historyUIState.value?.date
            calendar?.add(Calendar.DATE, 1)
            _historyUIState.value = historyUIState.value?.copy(date = calendar!!)
        }else {
            val calendar = _historyUIState.value?.date
            calendar?.add(Calendar.DATE, -1)
            _historyUIState.value = historyUIState.value?.copy(date = calendar!!)
        }
    }
}