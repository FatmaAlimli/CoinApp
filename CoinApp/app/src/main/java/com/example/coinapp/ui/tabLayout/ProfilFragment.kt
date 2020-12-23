package com.example.coinapp.ui.tabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.coinapp.R
import com.example.coinapp.databinding.FragmentProfilBinding
import com.example.coinapp.models.Coin
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilFragment : Fragment() {

    lateinit var binding: FragmentProfilBinding
    var coin: Coin? = null
    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profil, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.coin = coin
      //  binding.textId.text = coin?.id.toString()
        binding.textName.text = coin?.name
        binding.textPrice.text = coin?.price
        binding.textDescription.text = coin?.description

    }

}
