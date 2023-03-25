package com.ozgursarki.shoppinglist.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.domain.model.ShoppingItem
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.DetailListViewHolder
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.ShoppingListViewHolder
import com.ozgursarki.shoppinglist.presentation.enum.ViewHolderType

class ShoppingListAdapter(
    private val shoppingItemList: ArrayList<ShoppingItem> = arrayListOf(),
    private val viewHolderType: ViewHolderType,
    private val buttonCallback: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewHolderType) {
            ViewHolderType.DETAIL_VIEWHOLDER -> {
                DetailListViewHolder.create(parent)
            }
            ViewHolderType.SHOPPING_VIEWHOLDER -> {
                ShoppingListViewHolder.create(parent)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShoppingListViewHolder -> {
                holder.bind(shoppingItemList[position]) {
                    buttonCallback.invoke(it)
                }
            }
            is DetailListViewHolder -> {
                holder.bind(shoppingItemList[position])
            }
        }

    }

    override fun getItemCount(): Int = shoppingItemList.size

    fun setShoppingList(newShoppingItemList: ArrayList<ShoppingItem>) {
        shoppingItemList.clear()
        shoppingItemList.addAll(newShoppingItemList)
        notifyDataSetChanged()
    }

    fun isListEmpty(): Boolean {
        return shoppingItemList.isEmpty()
    }

    fun getShoppingList(): ArrayList<ShoppingItem> = shoppingItemList
}