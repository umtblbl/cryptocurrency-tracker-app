package com.umit.cryptocurrencytrackerapp.data.remote.dataSource

import com.umit.cryptocurrencytrackerapp.data.remote.api.CoinsAPI
import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinModel
import com.umit.cryptocurrencytrackerapp.shared.error.NetworkErrorType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CoinsRemoteDataSource @Inject constructor(private val coinsApi: CoinsAPI) {

    fun fetchCoinList(): Single<List<CoinModel>> {
        return coinsApi.fetchCoinList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { response ->
                Single.just(response ?: throw NetworkErrorType.IncorrectDataReturned())
            }
    }
}
