package com.umit.cryptocurrencytrackerapp.data.local.dataSource

import com.umit.cryptocurrencytrackerapp.data.local.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.coinEntity
import com.umit.cryptocurrencytrackerapp.shared.extensions.coinModel
import javax.inject.Inject

class CoinsLocalDataSource @Inject constructor(private val coinsDao: CoinsDao) {

    fun addCoin(coinList: List<CoinItemModel>) {
        coinsDao.add(*coinList.map { it.coinEntity }.toTypedArray())
    }

    fun searchCoin(text: String?): List<CoinItemModel> {
        return coinsDao.search("%$text%").map { it.coinModel }
    }

    fun getAllCoin(): List<CoinItemModel> {
        return coinsDao.getAll().map { it.coinModel }
    }
}
