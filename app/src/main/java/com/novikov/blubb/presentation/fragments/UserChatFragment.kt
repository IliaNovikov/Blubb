package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentUserChatBinding


class UserChatFragment : Fragment() {

    private val binding: FragmentUserChatBinding by lazy {
        FragmentUserChatBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }

}