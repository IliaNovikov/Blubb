package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.novikov.blubb.R
import com.novikov.blubb.adapters.MainFragmentTabsAdapter
import com.novikov.blubb.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val adapterViewPager by lazy {
        MainFragmentTabsAdapter(
            childFragmentManager,
            lifecycle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Chats"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Servers"))

        binding.viewPager.adapter = adapterViewPager

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.buttonSettings?.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_settingsFragment)
        }
        binding.buttonFriends?.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_friendsFragment)
        }
    }



}