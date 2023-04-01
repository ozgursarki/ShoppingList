package com.ozgursarki.shoppinglist.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgursarki.shoppinglist.databinding.RowItemHeaderBinding
import com.ozgursarki.shoppinglist.domain.model.ShoppingHeader

class HeaderViewHolder(
    private val binding: RowItemHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(shoppingHeader: ShoppingHeader, shoppingHeaderCallBack: (ShoppingHeader) -> Unit) {
        binding.itemHeader.text = shoppingHeader.title
        binding.root.setOnClickListener{
            shoppingHeaderCallBack.invoke(shoppingHeader)
        }
    }

    companion object {
        fun create(
            parent : ViewGroup
        ) : HeaderViewHolder {
            val view = RowItemHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return HeaderViewHolder(view)
        }
    }
}