package com.umit.cryptocurrencytrackerapp.data.local.dataSource

import com.umit.cryptocurrencytrackerapp.data.local.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity
import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.coinEntity
import javax.inject.Inject

class CoinsLocalDataSource @Inject constructor(
    private val coinsDao: CoinsDao
) {

    fun add(coinList: List<CoinModel>) {
        coinsDao.addCoin(*coinList.map { it.coinEntity }.toTypedArray())
    }

    fun fetchAllCoins(): List<CoinEntity> {
        return coinsDao.fetchCoinList()
    }
}
