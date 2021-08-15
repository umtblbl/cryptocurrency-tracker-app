package com.umit.cryptocurrencytrackerapp.shared.extensions

import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel

val CoinEntity.coinModel: CoinItemModel
    get() {
        return CoinItemModel(
            id = coinId,
            name = name,
            symbol = symbol,
            firstSymbolLetter = symbol?.first().toString()
        )
    }

val CoinItemModel.coinEntity: CoinEntity
    get() {
        return CoinEntity(
            coinId = id,
            name = name,
            symbol = symbol,
        )
    }
