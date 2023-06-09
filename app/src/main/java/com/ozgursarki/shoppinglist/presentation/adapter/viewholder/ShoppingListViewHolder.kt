package com.ozgursarki.shoppinglist.presentation.adapter.viewholder


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.RowShoppingItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.extension.convertToInt
import com.ozgursarki.shoppinglist.presentation.enum.ItemType

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
            when(shoppingItem.type) {
                ItemType.FRUIT.type -> {
                    binding.rowItemConstrain.setBackgroundColor(binding.root.context.getColor(R.color.BabyBlue))
                    binding.minusButton.setBackgroundResource(R.color.BabyBlue)
                    binding.plusButton.setBackgroundResource(R.color.BabyBlue)
                }
                ItemType.VEGETABLES.type -> {
                    binding.rowItemConstrain.setBackgroundColor(binding.root.context.getColor(R.color.LightGreen))
                    binding.minusButton.setBackgroundResource(R.color.LightGreen)
                    binding.plusButton.setBackgroundResource(R.color.LightGreen)
                }
                ItemType.SNACK.type -> {
                    binding.rowItemConstrain.setBackgroundColor(binding.root.context.getColor(R.color.RedOrange))
                    binding.minusButton.setBackgroundResource(R.color.RedOrange)
                    binding.plusButton.setBackgroundResource(R.color.RedOrange)
                }
                ItemType.OTHER.type -> {
                    binding.rowItemConstrain.setBackgroundColor(binding.root.context.getColor(R.color.Violet))
                    binding.minusButton.setBackgroundResource(R.color.Violet)
                    binding.plusButton.setBackgroundResource(R.color.Violet)
                }
                ItemType.BREAKFAST.type -> {
                    binding.rowItemConstrain.setBackgroundColor(binding.root.context.getColor(R.color.RedPink))
                    binding.minusButton.setBackgroundResource(R.color.RedPink)
                    binding.plusButton.setBackgroundResource(R.color.RedPink)

                }

            }
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