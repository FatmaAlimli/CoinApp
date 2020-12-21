package com.example.coinapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.coinapp.R
import com.example.coinapp.adapter.ViewPagerAdapter
import com.example.coinapp.api.GenericResult
import com.example.coinapp.databinding.FragmentDetailBinding
import com.example.coinapp.ui.tabLayout.ProfilFragment
import com.example.coinapp.utils.CoinProgressDialog
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {


    lateinit var binding: FragmentDetailBinding
    lateinit var tabLayout: TabLayout
    private val detailViewModel: DetailViewModel by viewModels()
    var profilFragment: ProfilFragment? = ProfilFragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        detailViewModel.getCoinDetail(args.id)

        detailViewModel.coinDetailLiveData.observe(viewLifecycleOwner, Observer { coinDetail ->
            when (coinDetail) {
                is GenericResult.Loading -> {
                    CoinProgressDialog.show(requireContext())
                }
                is GenericResult.Success -> {
                    CoinProgressDialog.close()


                    val pagerAdapter = ViewPagerAdapter(
                        this.requireContext(),
                        parentFragmentManager,
                        tab_layout.tabCount,
                        coinDetail.data?.data?.coins?.find { coin -> coin.id == args.id }
                    )
                    binding.viewPager.adapter = pagerAdapter

                    binding.viewPager.addOnPageChangeListener(
                        TabLayout.TabLayoutOnPageChangeListener(
                            tab_layout
                        )
                    )

                    binding.tabLayout.setupWithViewPager(binding.viewPager)
//                    tab_layout.addOnTabSelectedListener(object :
//                        TabLayout.OnTabSelectedListener {
//                        override fun onTabSelected(tab: TabLayout.Tab?) {
//
//                            binding.viewPager.currentItem = tab!!.position
//
//                            var coin = coinDetail.data?.data?.coins?.first()
//
//                                (pagerAdapter.getItem(0)).let {
//                                    if (coin != null) {
//                                        profilFragment?.update(coin,id)
//                                    }
//                                }
//
//
//                        }
//
//                        override fun onTabUnselected(tab: TabLayout.Tab?) {}
//
//                        override fun onTabReselected(tab: TabLayout.Tab?) {}
//                    })
                }

                is GenericResult.Failure -> {
                    CoinProgressDialog.close()
                    Toast.makeText(context, "Hata Oluştu", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}

