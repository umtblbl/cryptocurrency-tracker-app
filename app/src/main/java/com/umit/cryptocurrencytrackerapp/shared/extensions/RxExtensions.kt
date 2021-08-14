package com.umit.cryptocurrencytrackerapp.shared.extensions

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.disposed(by: CompositeDisposable): Disposable = apply { by.add(this) }
