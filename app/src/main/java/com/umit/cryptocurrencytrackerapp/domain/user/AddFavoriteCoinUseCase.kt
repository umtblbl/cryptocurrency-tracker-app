package com.umit.cryptocurrencytrackerapp.domain.user

import com.umit.cryptocurrencytrackerapp.data.repository.UserRepository
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AddFavoriteCoinUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Observable<Boolean>, AddFavoriteCoinUseCase.Params>() {

    data class Params(val id: String?, val symbol: String?, val name: String?)

    override fun invoke(params: Params?) = userRepository.addFavoriteCoin(
        id = params?.id,
        symbol = params?.symbol,
        name = params?.name
    )
}
