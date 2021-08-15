package com.umit.cryptocurrencytrackerapp.domain.coins

import com.umit.cryptocurrencytrackerapp.data.repository.CoinsRepository
import com.umit.cryptocurrencytrackerapp.scenes.coinDetail.model.CoinDetailItemModel
import com.umit.cryptocurrencytrackerapp.shared.utilities.UseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchCoinDetailUseCase @Inject constructor(
    private val coinListRepository: CoinsRepository
) : UseCase<Single<CoinDetailItemModel>, FetchCoinDetailUseCase.Params>() {

    data class Params(val id: String?)

    override fun invoke(params: Params?) = coinListRepository.fetchCoinDetail(params?.id)
}
