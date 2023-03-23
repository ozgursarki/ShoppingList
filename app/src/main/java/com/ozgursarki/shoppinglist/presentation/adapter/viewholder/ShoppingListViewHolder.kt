package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.RowShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.ShoppingItem

class ShoppingListViewHolder(
    private val binding: RowShoppingItemBinding
) : ViewHolder(binding.root) {

    fun bind(
        shoppingItem: ShoppingItem
    ) {
        binding.apply {
            groceryName.text = shoppingItem.name
            countText.text = shoppingItem.count.toString()
        }
    }

    companion object {
        fun create(
            parent : ViewGroup
        ) : ShoppingListViewHolder {
            val view = RowShoppingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ShoppingListViewHolder(view)
        }
    }
}