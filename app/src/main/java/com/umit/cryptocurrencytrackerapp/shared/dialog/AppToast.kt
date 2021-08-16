package com.umit.cryptocurrencytrackerapp.shared.dialog

import android.app.Activity
import androidx.core.content.ContextCompat
import com.tapadoo.alerter.Alerter
import com.umit.cryptocurrencytrackerapp.R

enum class ToastType(val titleResId: Int, val textResId: Int) {
    AuthInfoError(R.string.auth_toast_info_error_title, R.string.auth_toast_info_error_text)
}

class AppToast(val activity: Activity?, val type: ToastType) {

    fun show() {
        Alerter.create(activity ?: return)
            .setBackgroundColorInt(ContextCompat.getColor(activity, R.color.secondaryGold))
            .setTitle(activity.getString(type.titleResId))
            .setText(activity.getString(type.textResId))
            .setDuration(2000)
            .show()
    }
}
