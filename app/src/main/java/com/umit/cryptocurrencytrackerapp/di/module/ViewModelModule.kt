package com.umit.cryptocurrencytrackerapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umit.cryptocurrencytrackerapp.di.factory.ViewModelFactory
import com.umit.cryptocurrencytrackerapp.di.key.ViewModelKey
import com.umit.cryptocurrencytrackerapp.scenes.coinDetail.CoinDetailViewModel
import com.umit.cryptocurrencytrackerapp.scenes.coinList.CoinListViewModel
import com.umit.cryptocurrencytrackerapp.scenes.favoriteCoinList.FavoriteCoinListViewModel
import com.umit.cryptocurrencytrackerapp.scenes.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CoinListViewModel::class)
    abstract fun provideCoinListViewModel(coinListViewModel: CoinListViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(FavoriteCoinListViewModel::class)
    abstract fun provideFavoriteCoinListViewModel(favoriteCoinListViewModel: FavoriteCoinListViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CoinDetailViewModel::class)
    abstract fun provideCoinDetailViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel
}
