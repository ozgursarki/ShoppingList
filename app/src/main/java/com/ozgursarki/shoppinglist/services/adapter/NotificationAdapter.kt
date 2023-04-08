package com.ozgursarki.shoppinglist.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ozgursarki.shoppinglist.databinding.NotificationLayoutBinding
import com.ozgursarki.shoppinglist.databinding.RowDetailItemBinding
import com.ozgursarki.shoppinglist.databinding.RowNotificationItemBinding
import com.ozgursarki.shoppinglist.presentation.adapter.viewholder.DetailListViewHolder
import com.ozgursarki.shoppinglist.services.NotificationModel
import com.ozgursarki.shoppinglist.util.DateUtil

class NotificationAdapter(private val notifyList: List<NotificationModel> = listOf()): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(private val binding: RowNotificationItemBinding): ViewHolder(binding.root) {
        fun bind(notificationModel: NotificationModel ) {
            binding.apply {
                notificationText.text = DateUtil.getDateInTurkishWithoutHour(notificationModel.date)
                doneRate.text = notificationModel.rate.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = RowNotificationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifyList[position])
    }

    override fun getItemCount(): Int = notifyList.size
}