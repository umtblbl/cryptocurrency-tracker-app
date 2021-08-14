package com.umit.cryptocurrencytrackerapp

import android.app.Application
import com.umit.cryptocurrencytrackerapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CryptocurrencyTrackerApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        buildDagger()
    }

    private fun buildDagger() {
        AppInjector.init(this)
    }
}
