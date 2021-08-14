package com.umit.cryptocurrencytrackerapp.scenes.coinList

import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.umit.cryptocurrencytrackerapp.data.remote.model.CoinModel
import com.umit.cryptocurrencytrackerapp.domain.coins.FetchCoinListUseCase
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val fetchCoinListUseCase: FetchCoinListUseCase
) : ViewModel() {

    val stopRefreshLayout: PublishSubject<Unit> = PublishSubject.create()
    val coinListRelay = BehaviorRelay.create<List<CoinModel>>()

    fun getCoin() {
        fetchCoinList()
            .subscribeBy { coinList ->
                coinListRelay.accept(coinList)
            }.disposed(by = disposeBag)
    }

    private fun fetchCoinList(): Observable<List<CoinModel>> {
        return fetchCoinListUseCase()
            .compose(activityIndicator.trackActivitySingle())
            .toObservable()
            .compose(error.trackError())
            .onErrorResumeNext { error ->
                Log.d("ERROR:", error.toString())
                Observable.empty()
            }.doFinally { stopRefreshLayout.onNext(Unit) }
    }
}
