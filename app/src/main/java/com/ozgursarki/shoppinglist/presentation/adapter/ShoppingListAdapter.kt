package com.ozgursarki.shoppinglist.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.domain.ShoppingItem
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.ShoppingListViewHolder

class ShoppingListAdapter(
    private val shoppingItemList: ArrayList<ShoppingItem> = arrayListOf()
) : RecyclerView.Adapter<ShoppingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(shoppingItemList[position])
    }

    override fun getItemCount(): Int = shoppingItemList.size
}