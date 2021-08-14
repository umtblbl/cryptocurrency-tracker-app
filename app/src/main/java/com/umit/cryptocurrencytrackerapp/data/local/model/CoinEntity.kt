package com.umit.cryptocurrencytrackerapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "coinId") val coinId: String?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "name") val name: String?
)
