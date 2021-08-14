package com.umit.cryptocurrencytrackerapp.data.repository

import com.umit.cryptocurrencytrackerapp.data.local.dataSource.CoinsLocalDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private var coinsLocalDataSource: CoinsLocalDataSource
) {
    fun fetchCoinList(): Single<List<CoinModel>> {
        return coinsRemoteDataSource.fetchCoinList()
            .flatMap {
                addCoinList(it).subscribe()
                return@flatMap Single.just(it)
            }
    }

    private fun addCoinList(coinList: List<CoinModel>): Single<Unit> {
        return Single.fromCallable {
            coinsLocalDataSource.add(coinList)
        }.subscribeOn(Schedulers.io())
    }
}
