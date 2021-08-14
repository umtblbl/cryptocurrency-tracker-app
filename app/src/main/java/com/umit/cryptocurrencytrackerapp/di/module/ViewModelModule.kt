package com.umit.cryptocurrencytrackerapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umit.cryptocurrencytrackerapp.di.factory.ViewModelFactory
import com.umit.cryptocurrencytrackerapp.di.key.ViewModelKey
import com.umit.cryptocurrencytrackerapp.scenes.coinList.CoinListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(CoinListViewModel::class)
    abstract fun provideProductsViewModel(coinListViewModel: CoinListViewModel): ViewModel
}
