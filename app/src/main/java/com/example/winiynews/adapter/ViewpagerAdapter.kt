package com.example.winiynews.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author winiymissl
 * @Date 2024-05-29 19:55
 * @Version 1.0
 */
class ViewpagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle, private val fragments: List<Fragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
    }
}