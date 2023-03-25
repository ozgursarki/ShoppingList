package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.RowShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.extension.convertToInt

class ShoppingListViewHolder(
    private val binding: RowShoppingItemBinding,
) : ViewHolder(binding.root) {

    fun bind(
        shoppingItem: ShoppingItem,
        buttonCallback: (ShoppingItem) -> Unit
    ) {
        binding.apply {
            groceryName.text = shoppingItem.name
            countText.text = shoppingItem.count.toString()
        }

        binding.minusButton.setOnClickListener {
            if (binding.countText.text.convertToInt() > 1) {
                //binding.countText.text = (binding.countText.text.convertToInt() - 1).toString()
                shoppingItem.count = binding.countText.text.convertToInt() - 1
                buttonCallback.invoke(shoppingItem)
            }
        }

        binding.plusButton.setOnClickListener {
            //binding.countText.text = (binding.countText.text.convertToInt() + 1).toString()
            shoppingItem.count = binding.countText.text.convertToInt() + 1
            Log.e("ViewHolder-Plus", shoppingItem.count.toString())
            buttonCallback.invoke(shoppingItem)

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