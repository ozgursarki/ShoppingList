package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.R
import com.ozgursarki.shoppinglist.databinding.RowDetailItemBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.presentation.enum.ItemType

class DetailListViewHolder(
    private val binding: RowDetailItemBinding
) : ViewHolder(binding.root) {


    private lateinit var dataShoppingItem: ShoppingItem
    fun bind(shoppingItem: ShoppingItem) {
        dataShoppingItem = shoppingItem
        binding.apply {
            shoppingItemName.text = shoppingItem.name
            shoppingItemCount.text = shoppingItem.count.toString()

            when(shoppingItem.type) {
                ItemType.FRUIT.type -> {
                    binding.itemNameRoot.setBackgroundColor(binding.root.context.getColor(R.color.BabyBlue))
                    binding.itemCountRoot.setBackgroundColor(binding.root.context.getColor(R.color.BabyBlue))

                }
                ItemType.VEGETABLES.type -> {
                    binding.itemNameRoot.setBackgroundColor(binding.root.context.getColor(R.color.LightGreen))
                    binding.itemCountRoot.setBackgroundColor(binding.root.context.getColor(R.color.LightGreen))
                }
                ItemType.SNACK.type -> {
                    binding.itemNameRoot.setBackgroundColor(binding.root.context.getColor(R.color.RedOrange))
                    binding.itemCountRoot.setBackgroundColor(binding.root.context.getColor(R.color.RedOrange))
                }
                ItemType.OTHER.type -> {
                    binding.itemNameRoot.setBackgroundColor(binding.root.context.getColor(R.color.Violet))
                    binding.itemCountRoot.setBackgroundColor(binding.root.context.getColor(R.color.Violet))
                }
                ItemType.BREAKFAST.type -> {
                    binding.itemNameRoot.setBackgroundColor(binding.root.context.getColor(R.color.RedPink))
                    binding.itemCountRoot.setBackgroundColor(binding.root.context.getColor(R.color.RedPink))

                }

            }
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