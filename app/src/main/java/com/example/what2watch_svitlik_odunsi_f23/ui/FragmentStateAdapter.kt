package com.example.what2watch_svitlik_odunsi_f23.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

open class FragmentStateAdapter (fragment: Fragment, private val fragments: List<Fragment>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]

}