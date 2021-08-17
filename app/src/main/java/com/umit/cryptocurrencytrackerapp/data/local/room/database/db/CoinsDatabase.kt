package com.umit.cryptocurrencytrackerapp.data.local.room.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umit.cryptocurrencytrackerapp.data.local.room.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.local.room.model.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinsDao
}
