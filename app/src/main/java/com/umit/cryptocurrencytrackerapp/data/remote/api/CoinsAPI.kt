package com.umit.cryptocurrencytrackerapp.data.remote.api

import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinListModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CoinsAPI {

    @GET("coins/list")
    fun fetchCoinList(): Single<List<CoinListModel>>
}
