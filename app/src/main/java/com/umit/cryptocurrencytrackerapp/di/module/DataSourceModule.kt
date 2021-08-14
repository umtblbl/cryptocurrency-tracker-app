package com.umit.cryptocurrencytrackerapp.di.module

import com.umit.cryptocurrencytrackerapp.data.local.dataSource.CoinsLocalDataSource
import com.umit.cryptocurrencytrackerapp.data.local.database.dao.CoinsDao
import com.umit.cryptocurrencytrackerapp.data.remote.api.CoinsAPI
import com.umit.cryptocurrencytrackerapp.data.remote.dataSource.CoinsRemoteDataSource
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
}
