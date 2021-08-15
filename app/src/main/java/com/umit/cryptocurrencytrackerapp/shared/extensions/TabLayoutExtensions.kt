package com.umit.cryptocurrencytrackerapp.shared.extensions

import com.google.android.material.tabs.TabLayout
import io.reactivex.rxjava3.core.Observable

fun TabLayout.changes(): Observable<Int> {
    return Observable.create { emitter ->
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                emitter.onNext(tab?.position)
            }
        })
    }
}
