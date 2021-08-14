package com.umit.cryptocurrencytrackerapp.shared.utilities

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.subjects.PublishSubject

class ErrorTracker : Observable<Throwable>() {

    private val variable = PublishSubject.create<Throwable>()

    private fun <T> trackError(source: Observable<T>): Observable<T> {
        return source
            .doOnError {
                onError(it)
            }
    }

    private fun onError(throwable: Throwable) {
        variable.onNext(throwable)
    }

    override fun subscribeActual(observer: Observer<in Throwable>?) {
        variable.subscribe(observer)
    }

    fun <T> trackError(): ObservableTransformer<T, T> {
        return ObservableTransformer { source: Observable<T> ->
            this.trackError(
                source
            )
        }
    }
}
