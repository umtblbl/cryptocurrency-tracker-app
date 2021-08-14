package com.umit.cryptocurrencytrackerapp.shared.utilities

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RxActivityIndicator : Observable<Boolean>() {
    private val variable = BehaviorSubject.create<Int>()
    private var loading: Observable<Boolean>

    init {
        loading = variable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturnItem(0)
            .map { x: Int -> x > 0 }
            .share()
    }

    private fun <T> trackActivity(source: Observable<T?>): Observable<T> {
        val resourceFactory = Supplier {
            increment()
            Unit.getDefault()
        }
        val observableFactory =
            Function<Unit, Observable<T?>> { x: Unit? -> source }
        val disposer =
            Consumer<Unit> { unit: Unit? -> decrement() }
        return using(
            resourceFactory,
            observableFactory,
            disposer
        )
    }

    private fun <T> trackActivitySingle(source: Single<T?>): Single<T> {
        val resourceFactory =
            Supplier {
                increment()
                Unit.getDefault()
            }
        val observableFactory =
            Function<Unit, Single<T?>> { x: Unit? -> source }
        val disposer =
            Consumer<Unit> { unit: Unit? -> decrement() }
        return Single.using(resourceFactory, observableFactory, disposer)
    }

    override fun subscribeActual(observer: Observer<in Boolean>): kotlin.Unit {
        loading.subscribe(observer)
    }

    private fun increment() {
        if (variable.value == null) {
            variable.onNext(1)
        } else {
            variable.onNext(variable.value + 1)
        }
    }

    private fun decrement() {
        if (variable.value == null) {
            variable.onNext(0)
        } else {
            variable.onNext(variable.value - 1)
        }
    }

    fun <T> trackActivity(): ObservableTransformer<T?, T> {
        return ObservableTransformer { source: Observable<T?> ->
            this.trackActivity(
                source
            )
        }
    }

    fun <T> nonTrackActivity(): ObservableTransformer<T?, T> {
        return ObservableTransformer { create {} }
    }

    fun <T> trackActivitySingle(): SingleTransformer<T?, T> {
        return SingleTransformer { source: Single<T?> ->
            this.trackActivitySingle(
                source
            )
        }
    }
}
