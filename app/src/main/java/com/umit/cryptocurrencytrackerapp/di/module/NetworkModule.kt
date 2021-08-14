package com.umit.cryptocurrencytrackerapp.di.module

import android.os.Environment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.umit.cryptocurrencytrackerapp.data.remote.api.CoinsAPI
import com.umit.cryptocurrencytrackerapp.shared.Configs
import com.umit.cryptocurrencytrackerapp.shared.error.RxErrorHandlingAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val cache = Cache(Environment.getDownloadCacheDirectory(), Configs.Network.cacheSize)
        return OkHttpClient.Builder()
            .connectTimeout(Configs.Network.timeOut, TimeUnit.SECONDS)
            .readTimeout(Configs.Network.timeOut, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Configs.Network.baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinsAPI(retrofit: Retrofit): CoinsAPI {
        return retrofit.create(CoinsAPI::class.java)
    }
}
