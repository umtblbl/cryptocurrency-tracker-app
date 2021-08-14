package com.umit.cryptocurrencytrackerapp.di.component

import android.app.Application
import com.umit.cryptocurrencytrackerapp.CryptocurrencyTrackerApp
import com.umit.cryptocurrencytrackerapp.di.module.ActivityModule
import com.umit.cryptocurrencytrackerapp.di.module.AppModule
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
        ViewModelModule::class
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
