package com.umit.cryptocurrencytrackerapp.shared.dialog

import android.app.Activity
import androidx.core.content.ContextCompat
import com.tapadoo.alerter.Alerter
import com.umit.cryptocurrencytrackerapp.R

enum class ToastType(val titleResId: Int, val textResId: Int) {
    AuthInfoError(R.string.toast_info_error_title, R.string.toast_info_error_text),
    ProcessFailed(R.string.toast_process_failed_title, R.string.toast_process_failed_text),
    ProcessSuccessful(R.string.toast_process_successful_title, R.string.toast_process_failed_title)
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
