package com.umit.cryptocurrencytrackerapp.scenes.register

import com.umit.cryptocurrencytrackerapp.domain.auth.RegisterUseCase
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    val registerUseCase: RegisterUseCase
) : ViewModel() {

    val registerStateSubject: PublishSubject<Boolean> = PublishSubject.create()
    val registerActionSubject: PublishSubject<Pair<String?, String?>> = PublishSubject.create()

    init {
        registerActionSubject
            .flatMap {
                registerUser(it.first, it.second)
            }
            .subscribeBy { isSuccess ->
                registerStateSubject.onNext(isSuccess)
            }.disposed(by = disposeBag)
    }

    private fun registerUser(email: String?, password: String?): Observable<Boolean>? {
        return registerUseCase(RegisterUseCase.Params(email, password))
            .compose(error.trackError())
            .onErrorResumeNext { error ->
                Observable.empty()
            }
    }
}
