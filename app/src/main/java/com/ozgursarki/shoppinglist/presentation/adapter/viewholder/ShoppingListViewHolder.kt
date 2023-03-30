package com.ozgursarki.shoppinglist.presentation.adapter.viewholder


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.RowShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.extension.convertToInt

class ShoppingListViewHolder(
    private val binding: RowShoppingItemBinding,
) : ViewHolder(binding.root) {

    private lateinit var localeShoppingItem: ShoppingItem

    fun bind(
        shoppingItem: ShoppingItem,
        buttonCallback: (ShoppingItem) -> Unit
    ) {
        localeShoppingItem = shoppingItem

        binding.apply {
            groceryName.text = shoppingItem.name
            countText.text = shoppingItem.count.toString()
            date.text = shoppingItem.date
            itemType.text = shoppingItem.type
        }

        binding.minusButton.setOnClickListener {
            if (binding.countText.text.convertToInt() > 1) {
                shoppingItem.count = binding.countText.text.convertToInt() - 1
                buttonCallback.invoke(shoppingItem)
            }
        }

        binding.plusButton.setOnClickListener {
            shoppingItem.count = binding.countText.text.convertToInt() + 1
            buttonCallback.invoke(shoppingItem)

        }
    }

    fun getShoppingItem(): ShoppingItem? {
        return if (this::localeShoppingItem.isInitialized) {
            localeShoppingItem
        }else {
            null
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