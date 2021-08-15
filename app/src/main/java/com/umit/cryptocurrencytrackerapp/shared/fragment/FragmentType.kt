package com.umit.cryptocurrencytrackerapp.shared.fragment

import androidx.databinding.ViewDataBinding
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface FragmentType<VM : ViewModel, Binding : ViewDataBinding> {

    val disposeBag: CompositeDisposable
    val binding: Binding
    val viewModel: VM
    val layoutResId: Int

    fun setupView()
}
