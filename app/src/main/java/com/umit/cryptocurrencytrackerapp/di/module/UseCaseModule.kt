package com.umit.cryptocurrencytrackerapp.di.module

import com.umit.cryptocurrencytrackerapp.data.repository.CoinsRepository
import com.umit.cryptocurrencytrackerapp.domain.coins.FetchCoinListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchCoinListUseCase(coinsRepository: CoinsRepository): FetchCoinListUseCase {
        return FetchCoinListUseCase(coinsRepository)
    }
}
