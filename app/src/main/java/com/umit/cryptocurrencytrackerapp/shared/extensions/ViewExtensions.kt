package com.umit.cryptocurrencytrackerapp.shared.extensions

import android.view.View
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.cast

fun View.action(): Observable<Unit> {
    return clicks().cast()
}
