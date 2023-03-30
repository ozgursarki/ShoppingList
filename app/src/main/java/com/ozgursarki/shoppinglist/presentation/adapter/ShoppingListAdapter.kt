package com.ozgursarki.shoppinglist.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.domain.model.ShoppingHeader
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.DetailListViewHolder
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.HeaderViewHolder
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.ShoppingListViewHolder
import com.ozgursarki.shoppinglist.presentation.enum.ViewHolderType

class ShoppingListAdapter(
    private val shoppingItemList: ArrayList<Any> = arrayListOf(),
    private val viewHolderType: ViewHolderType,
    private val buttonCallback: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SHOPPING_HEADER -> {
                HeaderViewHolder.create(parent)
            }
            TYPE_SHOPPING_ITEM -> {
                ShoppingListViewHolder.create(parent)
            }
            else -> ShoppingListViewHolder.create(parent)
        }

    }

    companion object {
        private const val TYPE_SHOPPING_ITEM = 3
        private const val TYPE_SHOPPING_HEADER = 4
    }

    override fun getItemViewType(position: Int): Int {


        return when (shoppingItemList[position]) {
            is ShoppingItem -> TYPE_SHOPPING_ITEM
            is ShoppingHeader -> TYPE_SHOPPING_HEADER
            else -> TYPE_SHOPPING_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShoppingListViewHolder -> {
                holder.bind(shoppingItemList[position] as ShoppingItem) {
                    buttonCallback.invoke(it)
                }
            }
            is HeaderViewHolder -> {
                holder.bind(shoppingItemList[position] as ShoppingHeader)
            }
        }

    }

    override fun getItemCount(): Int = shoppingItemList.size

    fun setShoppingList(newShoppingItemList: ArrayList<Any>) {
        shoppingItemList.clear()
        shoppingItemList.addAll(newShoppingItemList)
        notifyDataSetChanged()
    }

    fun isListEmpty(): Boolean {
        return shoppingItemList.isEmpty()
    }

    fun getShoppingList(): ArrayList<Any> = shoppingItemList
}