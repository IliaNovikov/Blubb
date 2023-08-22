package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.novikov.blubb.R
import com.novikov.blubb.domain.models.Chat


class UserChatFragment : Fragment() {

    val navArgs: UserChatFragmentArgs by navArgs()
    val selectedChat: Chat by lazy { navArgs.selectedChat }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ABOBA", navArgs.selectedChat.id)
        return inflater.inflate(R.layout.fragment_user_chat, container, false)
    }
}