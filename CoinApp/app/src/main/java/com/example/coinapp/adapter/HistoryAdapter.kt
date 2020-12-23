package com.example.coinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapp.databinding.RowHistoryLayoutBinding
import com.example.coinapp.models.Coin


class HistoryAdapter(private var dataList: List<String>) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        RowHistoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}

class HistoryViewHolder(private val binding: RowHistoryLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(coin: String) {
        binding.textHistory.text = coin

    }
}