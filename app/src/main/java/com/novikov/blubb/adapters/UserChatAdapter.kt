package com.novikov.blubb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novikov.blubb.databinding.RecyclerViewUserChatBinding
import com.novikov.blubb.domain.models.Message

class UserChatAdapter(): RecyclerView.Adapter<UserChatAdapter.UserChatViewHolder>() {

    var data = emptyList<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserChatViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewUserChatBinding.inflate(inflater, parent, false)
        return UserChatViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserChatViewHolder, position: Int) {
        val message: Message = data[position]

        with(holder.binding){

        }
    }

    class UserChatViewHolder(val binding: RecyclerViewUserChatBinding):RecyclerView.ViewHolder(binding.root)
}