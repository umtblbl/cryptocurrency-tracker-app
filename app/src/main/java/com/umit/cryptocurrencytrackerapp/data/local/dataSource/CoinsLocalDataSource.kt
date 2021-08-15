package com.umit.cryptocurrencytrackerapp.data.local.dataSource

import com.umit.cryptocurrencytrackerapp.data.local.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.coinEntity
import javax.inject.Inject

class CoinsLocalDataSource @Inject constructor(
    private val coinsDao: CoinsDao
) {

    fun add(coinList: List<CoinItemModel>) {
        coinsDao.addCoin(*coinList.map { it.coinEntity }.toTypedArray())
    }

    fun fetchAllCoin(): List<CoinEntity> {
        return coinsDao.fetchCoinList()
    }
}
