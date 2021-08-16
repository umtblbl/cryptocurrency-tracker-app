package com.umit.cryptocurrencytrackerapp.scenes.register

import androidx.navigation.fragment.findNavController
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentRegisterBinding
import com.umit.cryptocurrencytrackerapp.shared.dialog.AppToast
import com.umit.cryptocurrencytrackerapp.shared.dialog.ToastType
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class RegisterFragment(
    override val layoutResId: Int = R.layout.fragment_register
) : BaseFragment<RegisterViewModel, FragmentRegisterBinding>(RegisterViewModel::class) {

    private val appErrorToast by lazy {
        AppToast(
            activity = activity,
            type = ToastType.AuthInfoError
        )
    }

    override fun setupView() {
        binding.loginNowTextView
            .action()
            .subscribeBy {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                )
            }.disposed(by = disposeBag)

        binding.registerButton
            .action()
            .subscribeBy {
                viewModel.registerActionSubject.onNext(
                    Pair(
                        binding.mailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    )
                )
            }.disposed(by = disposeBag)

        viewModel.registerStateSubject
            .subscribeBy { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                    )
                } else {
                    appErrorToast.show()
                }
            }.disposed(by = disposeBag)
    }
}
