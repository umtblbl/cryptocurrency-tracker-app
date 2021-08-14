package com.umit.cryptocurrencytrackerapp.shared.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.rxjava3.core.Observable

fun SwipeRefreshLayout.changes(): Observable<Unit> {
    return Observable.create { emitter ->
        setOnRefreshListener {
            emitter.onNext(Unit)
        }
    }
}
