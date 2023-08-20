package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentFriendsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    private lateinit var binding: FragmentFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFriendsBinding.inflate(layoutInflater)

        binding.buttonAddFriends.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_friendsFragment_to_searchUsersFragment)
        }

        return binding.root
    }

}