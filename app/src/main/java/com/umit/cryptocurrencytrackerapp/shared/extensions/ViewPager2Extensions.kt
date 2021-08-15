package com.umit.cryptocurrencytrackerapp.shared.extensions

import androidx.viewpager2.widget.ViewPager2
import io.reactivex.rxjava3.core.Observable

fun ViewPager2.changes(): Observable<Int> {
    return Observable.create { emitter ->
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                emitter.onNext(position)
            }
        })
    }
}
