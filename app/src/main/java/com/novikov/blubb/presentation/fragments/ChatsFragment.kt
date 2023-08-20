package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.novikov.blubb.R
import com.novikov.blubb.adapters.ChatsAdapter
import com.novikov.blubb.adapters.OnChatsRecyclerViewItemClickListener
import com.novikov.blubb.databinding.FragmentChatsBinding
import com.novikov.blubb.domain.models.Chat
import com.novikov.blubb.domain.models.Message
import com.novikov.blubb.domain.models.User
import java.time.LocalDateTime

class ChatsFragment : Fragment(),
OnChatsRecyclerViewItemClickListener{

    private lateinit var binding: FragmentChatsBinding
    private lateinit var adapter:ChatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ChatsAdapter(requireContext())

        binding.recyclerViewChats.adapter = adapter
        val testSender = User("6SlTh9TC2fgyMC9NePsmQZbBBbm1",
            "TestUser",
            "user@email.com")

        adapter.data = listOf(Chat("egge23F4f4",
            testSender,
            User("DOEB9pMwzCWChV3AKuJ2HmtA2hO2",
                "Rondvik",
                "rondvik@gmail.com"),
            mutableListOf(Message("Hello",
                LocalDateTime.of(2022,5,13,14,25),
                testSender))
            ))

    }

    override fun onItemClick(position: Int) {
        requireActivity().
                findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_mainFragment_to_userChatFragment)


        // TODO: сделать аргумет chat при навигации
        // TODO: как-то убрать сообщения из класса чата
        // TODO: user1 всегда текущий юзер
        // TODO: сделать постепенную загрузку сообщений

    }

}