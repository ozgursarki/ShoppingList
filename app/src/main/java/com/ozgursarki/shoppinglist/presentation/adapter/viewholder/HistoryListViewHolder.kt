package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.RowHistoryItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.util.DateUtil
import java.text.SimpleDateFormat
import java.util.*

class HistoryListViewHolder(
    private val binding: RowHistoryItemBinding
) : ViewHolder(binding.root) {

    private lateinit var localeShoppingList : ShoppingList

    fun bind(shoppingList: ShoppingList, itemClicked: (ShoppingList) -> Unit) {
        localeShoppingList = shoppingList

        binding.shoppingListName.text = DateUtil.getDateInTurkish(shoppingList.listID)

        binding.root.setOnClickListener{
            itemClicked.invoke(shoppingList)
        }

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