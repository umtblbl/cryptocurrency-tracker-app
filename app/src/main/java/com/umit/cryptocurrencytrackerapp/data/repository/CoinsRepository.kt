package com.umit.cryptocurrencytrackerapp.data.repository

import com.umit.cryptocurrencytrackerapp.data.local.dataSource.CoinsLocalDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.scenes.coinDetail.model.CoinDetailItemModel
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.error.NetworkErrorType
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private var coinsLocalDataSource: CoinsLocalDataSource
) {

    fun fetchCoinDetail(id: String?): Single<CoinDetailItemModel> {
        id ?: throw NetworkErrorType.RequestError()
        return coinsRemoteDataSource.fetchCoinDetail(id)
            .map { model ->
                CoinDetailItemModel(
                    id = model.id,
                    symbol = model.symbol,
                    name = model.name,
                    imageUrl = model.image.large,
                    description = model.description.en,
                    currentPrice = "${model.marketData.currentPrice["usd"].toString().substring(0, 5)} $",
                    pricePercentage24h = "${model.marketData.priceChangePercentage24HInCurrency["usd"].toString().substring(0, 4)} %"
                )
            }
    }

    fun fetchCoinList(): Single<List<CoinItemModel>> {
        return coinsRemoteDataSource.fetchCoinList()
            .flatMap { coinList ->
                coinList
                    .map {
                        CoinItemModel(
                            id = it.id,
                            name = it.name,
                            firstSymbolLetter = it.symbol?.first().toString().uppercase(Locale.getDefault()),
                            symbol = "[${it.symbol?.uppercase(Locale.getDefault())}]"
                        )
                    }.let { coinItemModels ->
                        addCoinList(coinItemModels).subscribe()
                        return@flatMap Single.just(coinItemModels)
                    }
            }
    }

    fun fetchSearchedCoinList(text: String?): Observable<List<CoinItemModel>> {
        return Single.fromCallable {
            if (text.isNullOrBlank())
                coinsLocalDataSource.getAllCoin()
            else
                coinsLocalDataSource.searchCoin(text)
        }.subscribeOn(Schedulers.io()).toObservable()
    }

    private fun addCoinList(coinList: List<CoinItemModel>): Single<Unit> {
        return Single.fromCallable {
            coinsLocalDataSource.addCoin(coinList)
        }.subscribeOn(Schedulers.io())
    }
}
