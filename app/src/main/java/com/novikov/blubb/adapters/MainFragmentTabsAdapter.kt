package com.novikov.blubb.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.novikov.blubb.ChatsFragment
import com.novikov.blubb.ServersFragment

class MainFragmentTabsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
       return if (position == 0){
           ChatsFragment()
       }
       else{
            ServersFragment()
       }
    }
}