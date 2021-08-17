package com.umit.cryptocurrencytrackerapp.domain.user

import com.umit.cryptocurrencytrackerapp.data.repository.UserRepository
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DeleteFavoriteCoinUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Observable<Boolean>, DeleteFavoriteCoinUseCase.Params>() {

    data class Params(val id: String?)

    override fun invoke(params: Params?) = userRepository.deleteFavoriteCoin(params?.id)
}
