package com.ozgursarki.shoppinglist.presentation.history

import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.util.DateUtil
import java.util.Calendar

data class HistoryUIState(
    val list: List<ShoppingList> = listOf(),
    val date: Calendar = Calendar.getInstance(DateUtil.getSystemLocale())
)