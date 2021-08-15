package com.umit.cryptocurrencytrackerapp.domain.coins

import com.umit.cryptocurrencytrackerapp.data.repository.CoinsRepository
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchSearchedCoinListUseCase @Inject constructor(
    private val coinListRepository: CoinsRepository
) : UseCase<Observable<List<CoinItemModel>>, FetchSearchedCoinListUseCase.Params>() {

    data class Params(val text: String?)

    override fun invoke(params: Params?) = coinListRepository.fetchSearchedCoinList(params?.text)
}
