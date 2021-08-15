package com.umit.cryptocurrencytrackerapp.data.repository

import com.umit.cryptocurrencytrackerapp.data.local.dataSource.CoinsLocalDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private var coinsLocalDataSource: CoinsLocalDataSource
) {
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

    private fun addCoinList(coinList: List<CoinItemModel>): Single<Unit> {
        return Single.fromCallable {
            coinsLocalDataSource.add(coinList)
        }.subscribeOn(Schedulers.io())
    }
}
