package com.example.coinapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coinapp.ui.tabLayout.NewsFragment
import com.example.coinapp.ui.tabLayout.ProfilFragment
import com.example.coinapp.ui.tabLayout.SocialFragment

@Suppress("DEPRECATION")
internal class ViewPagerAdapter(
    var context: Context,
    fragmentManager: FragmentManager ,
    var totalTabs: Int
) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                return ProfilFragment()
            }
            1 -> {
                return SocialFragment()
            }
            2 -> {
                return NewsFragment()
            }
            else -> getItem(position)

        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}