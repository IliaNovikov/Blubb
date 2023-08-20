package com.novikov.blubb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.novikov.blubb.databinding.RecyclerViewChatsBinding
import com.novikov.blubb.domain.models.Chat


class ChatsAdapter(
    private val context: Context,
    val listener: OnRecyclerViewItemClickListener? = null
) : RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    var data: List<Chat?> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewChatsBinding.inflate(inflater, parent, false)

        return ChatViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val book: Chat? = data[position]
    }


    class ChatViewHolder(val binding: RecyclerViewChatsBinding) :
        RecyclerView.ViewHolder(binding.root)
}


interface OnRecyclerViewItemClickListener {
    fun onItemClick(position: Int)
}