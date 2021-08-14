package com.umit.cryptocurrencytrackerapp.data.repository

import com.umit.cryptocurrencytrackerapp.data.remote.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinListModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource
) {
    fun fetchCoinList(): Single<List<CoinListModel>> {
        return coinsRemoteDataSource.fetchCoinList()
            .flatMap {
                addCoinList(it)
                return@flatMap Single.just(it)
            }
    }

    private fun addCoinList(coinList: List<CoinListModel>) {
    }
}
