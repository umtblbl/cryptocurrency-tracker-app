package com.umit.cryptocurrencytrackerapp.shared.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class AppDialog<Binding : ViewDataBinding>(
    context: Context,
    layoutId: Int
) : Dialog(context) {

    protected val disposeBag: CompositeDisposable by lazy { CompositeDisposable() }

    abstract fun setupView()

    val binding: Binding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        layoutId,
        null,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setOnCancelListener {
            disposeBag.clear()
            disposeBag.dispose()
        }
    }
}
