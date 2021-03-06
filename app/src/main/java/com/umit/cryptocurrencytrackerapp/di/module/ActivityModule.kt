package com.umit.cryptocurrencytrackerapp.di.module

import com.umit.cryptocurrencytrackerapp.di.scope.PerActivity
import com.umit.cryptocurrencytrackerapp.scenes.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity?
}
