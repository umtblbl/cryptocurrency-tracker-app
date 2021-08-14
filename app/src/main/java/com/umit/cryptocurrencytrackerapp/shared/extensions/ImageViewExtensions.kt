package com.umit.cryptocurrencytrackerapp.shared.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImageFromUrl(context: Context?, url: String?) {
    context?.let {
        Glide
            .with(it)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(this)
    }
}
