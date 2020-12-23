package com.example.coinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapp.databinding.RowSocialLayoutBinding
import com.example.coinapp.models.Social

class SocialAdapter(private var dataList: List<Social> , private val onClickItem:(url: String) -> Unit) : RecyclerView.Adapter<SocialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SocialViewHolder(
        RowSocialLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        holder.bind(dataList[position], onClickItem)
    }

}

class SocialViewHolder(private val binding: RowSocialLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(coin: Social, onClickItem: (url: String) -> Unit) {
        binding.coin = coin
        binding.cardView.setOnClickListener{ onClickItem(coin.url)}
    }
}