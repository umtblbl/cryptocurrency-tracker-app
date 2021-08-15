package com.umit.cryptocurrencytrackerapp.scenes.register

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentRegisterBinding
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment

class RegisterFragment(
    override val layoutResId: Int = R.layout.fragment_register
) : BaseFragment<RegisterViewModel, FragmentRegisterBinding>(RegisterViewModel::class) {

    override fun setupView() {
    }
}
