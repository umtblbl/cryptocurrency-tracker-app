package com.umit.cryptocurrencytrackerapp.scenes.login

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentLoginBinding
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment

class LoginFragment(
    override val layoutResId: Int = R.layout.fragment_login
) : BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class) {

    override fun setupView() {
    }
}
