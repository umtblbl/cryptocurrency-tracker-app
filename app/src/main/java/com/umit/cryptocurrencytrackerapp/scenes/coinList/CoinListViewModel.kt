package com.umit.cryptocurrencytrackerapp.scenes.coinList

import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.umit.cryptocurrencytrackerapp.domain.coins.FetchCoinListUseCase
import com.umit.cryptocurrencytrackerapp.domain.coins.FetchSearchedCoinListUseCase
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val fetchCoinListUseCase: FetchCoinListUseCase,
    private var fetchSearchedCoinListUseCase: FetchSearchedCoinListUseCase
) : ViewModel() {

    val stopRefreshLayout: PublishSubject<Unit> = PublishSubject.create()
    val fetchCoinsRelay: BehaviorRelay<Unit> = BehaviorRelay.createDefault(Unit)
    val fetchSearchedCoinsSubject: PublishSubject<String?> = PublishSubject.create()
    val coinListRelay: BehaviorRelay<List<CoinItemModel>> = BehaviorRelay.create()

    init {
        fetchCoinsRelay
            .flatMap {
                fetchCoinList()
            }.subscribeBy { coinList ->
                coinListRelay.accept(coinList)
            }.disposed(by = disposeBag)

        fetchSearchedCoinsSubject
            .flatMap { text ->
                fetchSearchedCoinListUseCase(FetchSearchedCoinListUseCase.Params(text))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { coinList ->
                coinListRelay.accept(coinList)
            }.disposed(by = disposeBag)
    }

    private fun fetchCoinList(): Observable<List<CoinItemModel>> {
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
