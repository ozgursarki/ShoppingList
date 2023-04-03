package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.RowDetailItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem

class DetailListViewHolder(
    private val binding: RowDetailItemBinding
) : ViewHolder(binding.root) {


    private lateinit var dataShoppingItem: ShoppingItem
    fun bind(shoppingItem: ShoppingItem) {
        dataShoppingItem = shoppingItem
        binding.apply {
            shoppingItemName.text = shoppingItem.name
            shoppingItemCount.text = shoppingItem.count.toString()
        }
    }

    companion object {
        fun create(
            parent : ViewGroup
        ) : DetailListViewHolder {
            val view = RowDetailItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return DetailListViewHolder(view)
        }
    }

    fun getShoppingItem(): ShoppingItem? {
        return if (this::dataShoppingItem.isInitialized) {
            dataShoppingItem
        }else {
            null
        }
    }

}