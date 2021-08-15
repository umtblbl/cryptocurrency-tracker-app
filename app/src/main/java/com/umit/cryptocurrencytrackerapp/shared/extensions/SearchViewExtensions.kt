package com.umit.cryptocurrencytrackerapp.shared.extensions

import androidx.appcompat.widget.SearchView
import com.umit.cryptocurrencytrackerapp.shared.model.SearchViewModel
import io.reactivex.rxjava3.core.Observable

fun SearchView.changes(): Observable<SearchViewModel> {
    return Observable.create { emitter ->
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                emitter.onNext(SearchViewModel(text = query, isSubmit = true))
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                emitter.onNext(SearchViewModel(text = newText))
                return true
            }
        })
    }
}
