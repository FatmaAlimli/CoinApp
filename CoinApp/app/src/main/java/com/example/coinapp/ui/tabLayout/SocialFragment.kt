package com.example.coinapp.ui.tabLayout

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.coinapp.R
import com.example.coinapp.adapter.SocialAdapter
import com.example.coinapp.databinding.FragmentSocialBinding
import com.example.coinapp.models.Coin
import com.example.coinapp.models.Social
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocialFragment : Fragment() {

    lateinit var binding: FragmentSocialBinding
    var coin: Coin? = null
    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_social, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coin?.socials?.toList()?.let {
            binding.recyclerView.adapter = SocialAdapter(it){ url->
                val uri = Uri.parse(url)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }


    }
}