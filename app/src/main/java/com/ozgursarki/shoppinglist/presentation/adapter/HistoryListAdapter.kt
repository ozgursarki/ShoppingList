package com.ozgursarki.shoppinglist.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.domain.model.ShoppingList
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.HistoryListViewHolder

class HistoryListAdapter(
    private val historyItemList: ArrayList<ShoppingList> = arrayListOf(),
    private val itemClicked: (ShoppingList) -> Unit
) : RecyclerView.Adapter<HistoryListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        return HistoryListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        holder.bind(historyItemList[position]) {
            itemClicked.invoke(it)
        }
    }

    override fun getItemCount(): Int = historyItemList.size

    fun setHistoryShoppingList(newHistoryItemList: ArrayList<ShoppingList>) {
        historyItemList.clear()
        historyItemList.addAll(newHistoryItemList)
        notifyDataSetChanged()
    }

    fun getHistoryItemList(): ArrayList<ShoppingList> {
        return historyItemList
    }
}