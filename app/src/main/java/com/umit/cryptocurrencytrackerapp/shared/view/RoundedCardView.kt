package com.umit.cryptocurrencytrackerapp.shared.view

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

class RoundedCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
        radius = (width / 2).toFloat()
    }
}
