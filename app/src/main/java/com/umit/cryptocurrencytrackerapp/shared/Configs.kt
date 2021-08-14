package com.umit.cryptocurrencytrackerapp.shared

import com.umit.cryptocurrencytrackerapp.BuildConfig

object Configs {
    object Network {
        const val baseURL: String = BuildConfig.ROOT_URL
        const val timeOut: Long = 3 * 60
        const val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
    }

    object Database {
        const val coinsDatabase = "coins.db"
    }
}
