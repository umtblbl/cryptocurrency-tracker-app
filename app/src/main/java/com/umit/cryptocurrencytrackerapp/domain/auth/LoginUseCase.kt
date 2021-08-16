package com.umit.cryptocurrencytrackerapp.domain.auth

import com.umit.cryptocurrencytrackerapp.data.repository.AuthRepository
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Observable<Boolean>, LoginUseCase.Params>() {

    data class Params(val email: String?, val password: String?)

    override fun invoke(params: Params?) = authRepository.login(params?.email, params?.password)
}
