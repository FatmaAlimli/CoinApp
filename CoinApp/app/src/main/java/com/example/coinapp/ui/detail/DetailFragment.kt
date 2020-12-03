package com.example.coinapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.coinapp.R
import com.example.coinapp.adapter.ViewPagerAdapter
import com.example.coinapp.api.GenericResult
import com.example.coinapp.databinding.FragmentDetailBinding
import com.example.coinapp.utils.CoinProgressDialog
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private val detailViewModel: DetailViewModel by viewModels()

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
                    binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
                    val adapter = ViewPagerAdapter(requireContext(),, tabLayout.tabCount)
                    viewPager.adapter = adapter
                }

                is GenericResult.Failure -> {
                    CoinProgressDialog.close()
                    Toast.makeText(context, "Hata Oluştu", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}