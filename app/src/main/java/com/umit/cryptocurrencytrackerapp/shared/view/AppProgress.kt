package com.umit.cryptocurrencytrackerapp.shared.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.umit.cryptocurrencytrackerapp.R

class AppProgress(context: Context) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_app_progress)
        setCancelable(false)
        window?.let {
            it.attributes.dimAmount = 0.0f
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }
}
