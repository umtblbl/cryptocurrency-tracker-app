package com.umit.cryptocurrencytrackerapp.data.remote.api

import com.umit.cryptocurrencytrackerapp.data.remote.api.model.CoinDetailModel
import com.umit.cryptocurrencytrackerapp.data.remote.api.model.CoinModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsAPI {

    @GET("coins/list")
    fun fetchCoinList(): Single<List<CoinModel>>

    @GET("coins/{id}")
    fun fetchCoinDetail(
        @Path("id") id: String
    ): Single<CoinDetailModel>
}
