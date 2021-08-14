package com.umit.cryptocurrencytrackerapp.di.module

import android.content.Context
import androidx.room.Room
import com.umit.cryptocurrencytrackerapp.data.local.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.local.database.db.CoinsDatabase
import com.umit.cryptocurrencytrackerapp.shared.Configs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): CoinsDatabase {
        return Room.databaseBuilder(
            context,
            CoinsDatabase::class.java,
            Configs.Database.coinsDatabase
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideLogDao(coinsDatabase: CoinsDatabase): CoinsDao {
        return coinsDatabase.coinDao()
    }
}
