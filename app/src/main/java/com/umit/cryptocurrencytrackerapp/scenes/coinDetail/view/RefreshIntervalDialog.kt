package com.umit.cryptocurrencytrackerapp.scenes.coinDetail.view

import android.content.Context
import androidx.core.content.ContextCompat
import com.jakewharton.rxrelay3.BehaviorRelay
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.DialogRefreshIntervalBinding
import com.umit.cryptocurrencytrackerapp.shared.dialog.AppDialog
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import io.reactivex.rxjava3.kotlin.subscribeBy

enum class IntervalType(val value: Long) {
    Second5(5),
    Second30(30),
    Minute1(60),
    Minute5(300)
}

class RefreshIntervalDialog(context: Context) :
    AppDialog<DialogRefreshIntervalBinding>(context, R.layout.dialog_refresh_interval) {

    val typeAction: BehaviorRelay<IntervalType> = BehaviorRelay.create()

    override fun setupView() {
        window?.setBackgroundDrawable(
            ContextCompat.getDrawable(context, R.drawable.item_coin_bg)
        )

        binding.second5Radio
            .action()
            .subscribeBy {
                typeAction.accept(IntervalType.Second5)
                super.dismiss()
            }.disposed(by = disposeBag)

        binding.second30Radio
            .action()
            .subscribeBy {
                typeAction.accept(IntervalType.Second30)
                super.dismiss()
            }.disposed(by = disposeBag)

        binding.minute1Radio
            .action()
            .subscribeBy {
                typeAction.accept(IntervalType.Minute1)
                super.dismiss()
            }.disposed(by = disposeBag)

        binding.minute5Radio
            .action()
            .subscribeBy {
                typeAction.accept(IntervalType.Minute5)
                super.dismiss()
            }.disposed(by = disposeBag)
    }
}
