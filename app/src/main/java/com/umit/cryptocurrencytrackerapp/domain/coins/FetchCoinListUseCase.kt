package com.umit.cryptocurrencytrackerapp.domain.coins

import com.umit.cryptocurrencytrackerapp.data.repository.CoinsRepository
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchCoinListUseCase @Inject constructor(
    private val coinListRepository: CoinsRepository
) : UseCase<Single<List<CoinItemModel>>, UseCase.None>() {

    override fun invoke(params: None?) = coinListRepository.fetchCoinList()
}
