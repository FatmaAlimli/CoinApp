package com.example.coinapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.coinapp.R
import com.example.coinapp.adapter.CoinAdapter
import com.example.coinapp.api.GenericResult
import com.example.coinapp.databinding.FragmentHomeBinding
import com.example.coinapp.utils.CoinProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getCoin()
        homeViewModel.coinLiveData.observe(viewLifecycleOwner, Observer { coin ->
            when (coin) {
                is GenericResult.Loading -> {
                    CoinProgressDialog.show(requireContext())
                }
                is GenericResult.Success -> {
                    CoinProgressDialog.close()
                    coin.data?.data?.coins?.toList()?.let {
                        binding.recyclerView.adapter = CoinAdapter(it) { id ->
                            val action =
                                HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
                            findNavController().navigate(action)

                        }

                    }
                }

                is GenericResult.Failure -> {
                    CoinProgressDialog.close()
                    Toast.makeText(context, "Hata Oluştu", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

}