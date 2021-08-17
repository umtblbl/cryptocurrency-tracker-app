package com.umit.cryptocurrencytrackerapp.di.module

import com.umit.cryptocurrencytrackerapp.data.local.room.dataSource.CoinsLocalDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.api.dataSource.CoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.data.remote.fireStore.dataStore.FavoriteCoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.data.repository.AuthRepository
import com.umit.cryptocurrencytrackerapp.data.repository.CoinsRepository
import com.umit.cryptocurrencytrackerapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCoinsRepository(
        coinsRemoteDataSource: CoinsRemoteDataSource,
        coinsLocalDataSource: CoinsLocalDataSource
    ): CoinsRepository {
        return CoinsRepository(coinsRemoteDataSource, coinsLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepository()
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        favoriteCoinsRemoteDataSource: FavoriteCoinsRemoteDataSource
    ): UserRepository {
        return UserRepository(favoriteCoinsRemoteDataSource)
    }
}
