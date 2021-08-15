package com.umit.cryptocurrencytrackerapp.shared.extensions

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.timer(interval: Long): Observable<T> {
    return Observable.interval(interval, TimeUnit.SECONDS).flatMap { return@flatMap this }
}
