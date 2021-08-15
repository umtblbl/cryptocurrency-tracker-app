package com.umit.cryptocurrencytrackerapp.shared.extensions

import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel

val CoinEntity.coinModel: CoinItemModel
    get() {
        return CoinItemModel(
            id = id,
            name = name,
            symbol = symbol,
            firstSymbolLetter = symbol?.getOrNull(1)?.toString()
        )
    }

val CoinItemModel.coinEntity: CoinEntity
    get() {
        return CoinEntity(
            id = id ?: "",
            name = name,
            symbol = symbol,
        )
    }
