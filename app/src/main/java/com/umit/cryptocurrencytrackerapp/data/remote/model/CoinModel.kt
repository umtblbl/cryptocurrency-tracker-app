package com.umit.cryptocurrencytrackerapp.data.remote.model

import java.io.Serializable

data class CoinModel(
    val id: String?,
    val symbol: String?,
    val name: String?
) : Serializable {
    var color: Int = -1
}
