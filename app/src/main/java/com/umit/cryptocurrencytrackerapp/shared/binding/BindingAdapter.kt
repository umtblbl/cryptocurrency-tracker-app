package com.umit.cryptocurrencytrackerapp.shared.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.umit.cryptocurrencytrackerapp.shared.extensions.loadImageFromUrl

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(url: String?) {
    when {
        !url.isNullOrEmpty() -> {
            this.loadImageFromUrl(context, url)
        }
    }
}
