package com.umit.cryptocurrencytrackerapp.di.module

import com.umit.cryptocurrencytrackerapp.data.local.room.dataSource.CoinsLocalDataSource
import com.umit.cryptocurrencytrackerapp.data.local.room.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.remote.api.CoinsAPI
import com.umit.cryptocurrencytrackerapp.data.remote.api.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.fireStore.dataStore.FavoriteCoinsRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideCoinsRemoteDataSource(coinsAPI: CoinsAPI): CoinsRemoteDataSource {
        return CoinsRemoteDataSource(coinsAPI)
    }

    @Singleton
    @Provides
    fun provideCoinsLocalDataSource(coinsDao: CoinsDao): CoinsLocalDataSource {
        return CoinsLocalDataSource(coinsDao)
    }

    @Singleton
    @Provides
    fun provideFavoriteCoinsRemoteDataSource(): FavoriteCoinsRemoteDataSource {
        return FavoriteCoinsRemoteDataSource()
    }
}
