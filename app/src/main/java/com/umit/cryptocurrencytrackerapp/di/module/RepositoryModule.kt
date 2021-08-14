package com.umit.cryptocurrencytrackerapp.di.module

import com.umit.cryptocurrencytrackerapp.data.remote.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.data.repository.CoinsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCoinsRepository(coinsRemoteDataSource: CoinsRemoteDataSource): CoinsRepository {
        return CoinsRepository(coinsRemoteDataSource)
    }
}
