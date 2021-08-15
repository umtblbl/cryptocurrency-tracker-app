package com.umit.cryptocurrencytrackerapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umit.cryptocurrencytrackerapp.data.local.model.CoinEntity

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg coin: CoinEntity): LongArray

    @Query("SELECT * FROM coin WHERE symbol LIKE :text")
    fun search(text: String?): List<CoinEntity>

    @Query("SELECT * FROM coin")
    fun getAll(): List<CoinEntity>
}
