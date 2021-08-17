package com.umit.cryptocurrencytrackerapp.domain.user

import com.umit.cryptocurrencytrackerapp.data.repository.UserRepository
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetAllFavoriteCoinUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Observable<List<CoinItemModel>>, UseCase.None>() {

    override fun invoke(params: None?) = userRepository.getAllFavoriteCoin()
}
