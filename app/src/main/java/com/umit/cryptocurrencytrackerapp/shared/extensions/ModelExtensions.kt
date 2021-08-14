package com.umit.cryptocurrencytrackerapp.shared.extensions

import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity
import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinModel

val CoinEntity.coinModel: CoinModel
    get() {
        return CoinModel(
            id = coinId,
            name = name,
            symbol = symbol,
        )
    }

val CoinModel.coinEntity: CoinEntity
    get() {
        return CoinEntity(
            coinId = id,
            name = name,
            symbol = symbol
        )
    }
