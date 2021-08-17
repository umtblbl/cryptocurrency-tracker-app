package com.umit.cryptocurrencytrackerapp.scenes.login

import com.umit.cryptocurrencytrackerapp.domain.auth.LoginUseCase
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : ViewModel() {

    val loginStateSubject: PublishSubject<Boolean> = PublishSubject.create()
    val loginActionSubject: PublishSubject<Pair<String?, String?>> = PublishSubject.create()

    init {
        loginActionSubject
            .flatMap {
                loginUser(it.first, it.second)
            }
            .subscribeBy { isSuccess ->
                loginStateSubject.onNext(isSuccess)
            }.disposed(by = disposeBag)
    }

    private fun loginUser(email: String?, password: String?): Observable<Boolean>? {
        return loginUseCase(LoginUseCase.Params(email, password))
            .compose(error.trackError())
            .onErrorResumeNext {
                Observable.empty()
            }
    }
}
