package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.RowHistoryItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import java.text.SimpleDateFormat
import java.util.*

class HistoryListViewHolder(
    private val binding: RowHistoryItemBinding
) : ViewHolder(binding.root) {

    private lateinit var localeShoppingList : ShoppingList

    fun bind(shoppingList: ShoppingList) {
        localeShoppingList = shoppingList
        val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale("tr"))

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = shoppingList.listID
        binding.shoppingListName.text = formatter.format(calendar.time)

    }

    fun getShoppingList(): ShoppingList? {
        return if (this::localeShoppingList.isInitialized) {
            localeShoppingList
        }else {
            null
        }
    }

    companion object {
        fun create(
            parent : ViewGroup
        ) : HistoryListViewHolder {
            val view = RowHistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return HistoryListViewHolder(view)
        }
    }
}