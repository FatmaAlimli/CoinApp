package com.example.coinapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coinapp.models.Coin
import com.example.coinapp.ui.tabLayout.HistoryFragment
import com.example.coinapp.ui.tabLayout.ProfilFragment
import com.example.coinapp.ui.tabLayout.SocialFragment

@Suppress("DEPRECATION")
internal class ViewPagerAdapter(
    var context: Context,
    fragmentManager: FragmentManager,
    var totalTabs: Int,
    val coin: Coin?
) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return ProfilFragment().apply {

                    this.coin = this@ViewPagerAdapter.coin
                    this.id = this@ViewPagerAdapter.coin?.id
                }

            }
            1 -> {
                return SocialFragment()
            }
            2 -> {
                return HistoryFragment()
            }
            else -> {
                return getItem(position)
            }

        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Profil"
            }
            1 -> {
                return "Social"
            }
            2 -> {
                return "History"
            }
        }
        return super.getPageTitle(position)
    }
}
