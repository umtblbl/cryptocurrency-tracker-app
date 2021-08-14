package com.umit.cryptocurrencytrackerapp.shared.fragment

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface FragmentType<VM : ViewModel, Binding : ViewDataBinding> {

    val disposeBag: CompositeDisposable
    val binding: Binding
    val viewModel: VM

    @LayoutRes
    fun layoutResourceId(): Int
    fun setupView()
}
