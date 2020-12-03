package com.example.coinapp.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


object DataBindingUtils {
    @JvmStatic
    @BindingAdapter("loadCoinImage")
    fun loadCoinImage(imageView: ImageView, url: String?) {
        GlideToVectorYou
            .init()
            .with(imageView.context)
            .load(Uri.parse(url),imageView)

    }


}