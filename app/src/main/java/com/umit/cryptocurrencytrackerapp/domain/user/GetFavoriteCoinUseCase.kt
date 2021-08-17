package com.umit.cryptocurrencytrackerapp.domain.user

import com.umit.cryptocurrencytrackerapp.data.repository.UserRepository
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.model.Optional
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetFavoriteCoinUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Observable<Optional<CoinItemModel>>, GetFavoriteCoinUseCase.Params>() {

    data class Params(val id: String?)

    override fun invoke(params: Params?) = userRepository.getFavoriteCoin(params?.id)
}
