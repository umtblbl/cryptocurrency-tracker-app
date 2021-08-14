package com.umit.cryptocurrencytrackerapp.data.local.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umit.cryptocurrencytrackerapp.data.local.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinsDao
}
