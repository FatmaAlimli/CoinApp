package com.example.coinapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapp.databinding.RowCoinLayoutBinding
import com.example.coinapp.models.Coin
import java.lang.Exception
import java.math.RoundingMode


class CoinAdapter(private var dataList: List<Coin>, private val onClickItem: (id: Int) -> Unit) :
    RecyclerView.Adapter<CoinViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CoinViewHolder(
        RowCoinLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(dataList[position], onClickItem)
    }

    override fun getItemCount() = dataList.size


}

class CoinViewHolder(private val binding: RowCoinLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(coin: Coin, onClickItem: (id: Int) -> Unit) {

        binding.coin = coin
        if (coin.color != null) {
            try {
                binding.textSymbol.setTextColor(Color.parseColor(coin.color))
                binding.textPrice.setTextColor(Color.parseColor(coin.color))
            } catch(e : Exception){
                binding.textSymbol.setTextColor(Color.rgb(0, 0, 0))
                binding.textPrice.setTextColor(Color.rgb(0, 0, 0))
            }
        }

        binding.textPrice.text = coin.price.toBigDecimal().setScale(2, RoundingMode.UP).toDouble().toString()
        binding.cvItemCardContainer.setOnClickListener { onClickItem(coin.id) }

    }
}