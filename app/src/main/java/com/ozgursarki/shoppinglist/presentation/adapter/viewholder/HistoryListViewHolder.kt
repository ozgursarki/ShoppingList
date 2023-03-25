package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.FragmentHistoryBinding
import com.ozgursarki.shoppinglist.databinding.RowHistoryItemBinding
import com.ozgursarki.shoppinglist.databinding.RowShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingList

class HistoryListViewHolder(
    private val binding: RowHistoryItemBinding
) : ViewHolder(binding.root) {

    fun bind(shoppingList: ShoppingList) {

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