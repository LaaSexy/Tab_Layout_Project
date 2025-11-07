package com.example.tab_layout_project.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tab_layout_project.fragments.HistoryFragment
import com.example.tab_layout_project.fragments.HomeFragment
import com.example.tab_layout_project.fragments.PaymentFragment
import com.example.tab_layout_project.fragments.ProfileFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> PaymentFragment()
            2 -> HistoryFragment()
            3 -> ProfileFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}