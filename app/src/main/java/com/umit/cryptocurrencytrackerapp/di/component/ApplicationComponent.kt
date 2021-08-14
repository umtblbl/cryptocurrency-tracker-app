package com.umit.cryptocurrencytrackerapp.di.component

import android.app.Application
import com.umit.cryptocurrencytrackerapp.CryptocurrencyTrackerApp
import com.umit.cryptocurrencytrackerapp.di.module.ActivityModule
import com.umit.cryptocurrencytrackerapp.di.module.AppModule
import com.umit.cryptocurrencytrackerapp.di.module.DataSourceModule
import com.umit.cryptocurrencytrackerapp.di.module.FragmentBuildersModule
import com.umit.cryptocurrencytrackerapp.di.module.NetworkModule
import com.umit.cryptocurrencytrackerapp.di.module.RepositoryModule
import com.umit.cryptocurrencytrackerapp.di.module.UseCaseModule
import com.umit.cryptocurrencytrackerapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentBuildersModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)

interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: CryptocurrencyTrackerApp)
}
