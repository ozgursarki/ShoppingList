package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.FragmentDetailBinding
import com.ozgursarki.shoppinglist.databinding.RowDetailItemBinding
import com.ozgursarki.shoppinglist.databinding.RowShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem

class DetailListViewHolder(
    private val binding: RowDetailItemBinding
) : ViewHolder(binding.root) {

    fun bind(shoppingItem: ShoppingItem) {
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

}