package com.umit.cryptocurrencytrackerapp.shared.error

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

internal class RxErrorHandlingAdapterFactory : CallAdapter.Factory() {

    private val original = RxJava3CallAdapterFactory.create()

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return RxCallAdapterWrapper(wrapped = original.get(returnType, annotations, retrofit) ?: return null)
    }

    private class RxCallAdapterWrapper<R>(private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Any> {

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<R>): Any {
            return when (val result = wrapped.adapt(call)) {
                is Single<*> -> result.onErrorResumeNext { throwable ->
                    Single.error(throwable)
                }
                is Observable<*> -> result.onErrorResumeNext { throwable ->
                    Observable.error(asNetworkErrorType(throwable))
                }
                is Completable -> result.onErrorResumeNext { throwable ->
                    Completable.error(asNetworkErrorType(throwable))
                }
                else -> result
            }
        }

        private fun asNetworkErrorType(throwable: Throwable): NetworkErrorType {
            return when (throwable) {
                is NetworkErrorType -> {
                    throwable
                }
                is HttpException -> {
                    when (throwable.code()) {
                        404, 422, in 500..511 -> {
                            NetworkErrorType.NotReachedServer()
                        }
                        else -> {
                            NetworkErrorType.Cancelled()
                        }
                    }
                }
                is IOException -> {
                    NetworkErrorType.IOError()
                }
                else -> {
                    NetworkErrorType.Unknown()
                }
            }
        }
    }
}
