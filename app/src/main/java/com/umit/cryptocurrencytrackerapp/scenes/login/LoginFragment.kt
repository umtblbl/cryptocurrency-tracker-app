package com.umit.cryptocurrencytrackerapp.scenes.login

import androidx.navigation.fragment.findNavController
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentLoginBinding
import com.umit.cryptocurrencytrackerapp.shared.dialog.AppToast
import com.umit.cryptocurrencytrackerapp.shared.dialog.ToastType
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class LoginFragment(
    override val layoutResId: Int = R.layout.fragment_login
) : BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class) {

    private val errorToast by lazy { AppToast(activity = activity, type = ToastType.AuthInfoError) }

    override fun setupView() {

        binding.registerNowTextView
            .action()
            .subscribeBy {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }.disposed(by = disposeBag)

        binding.loginButton
            .action()
            .subscribeBy {
                viewModel.loginActionSubject.onNext(
                    Pair(
                        binding.mailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    )
                )
            }.disposed(by = disposeBag)

        viewModel.loginStateSubject
            .subscribeBy { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    )
                } else {
                    errorToast.show()
                }
            }.disposed(by = disposeBag)
    }
}
