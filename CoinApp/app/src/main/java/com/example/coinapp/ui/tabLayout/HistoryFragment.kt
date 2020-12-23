package com.example.coinapp.ui.tabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.coinapp.R
import com.example.coinapp.adapter.HistoryAdapter
import com.example.coinapp.databinding.FragmentHistoryBinding
import com.example.coinapp.models.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding
    var coin: Coin? = null
    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coin?.history?.toList()?.let {
            binding.recyclerView.adapter = HistoryAdapter(it)
        }


    }

}