package com.umit.cryptocurrencytrackerapp.data.remote.model

import java.io.Serializable

data class CoinListModel(
    val id: String?,
    val symbol: String?,
    val name: String?
) : Serializable
