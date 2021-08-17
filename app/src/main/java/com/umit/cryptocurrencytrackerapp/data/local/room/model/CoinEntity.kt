package com.umit.cryptocurrencytrackerapp.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class CoinEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "name") val name: String?
)
