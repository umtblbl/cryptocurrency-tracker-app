package com.umit.cryptocurrencytrackerapp.scenes.coinDetail

import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.umit.cryptocurrencytrackerapp.domain.coins.FetchCoinDetailUseCase
import com.umit.cryptocurrencytrackerapp.scenes.coinDetail.model.CoinDetailItemModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.extensions.timer
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val fetchCoinDetailUseCase: FetchCoinDetailUseCase
) : ViewModel() {

    // RefreshLayout
    val stopRefreshLayout: PublishSubject<Unit> = PublishSubject.create()
    // Data Call Subject
    val fetchCoinDetailRelay: BehaviorRelay<String> = BehaviorRelay.create()
    // Data
    val coinDetailSubject: PublishSubject<CoinDetailItemModel> = PublishSubject.create()
    // Timer
    val coinRefreshingIntervalRelay: BehaviorRelay<Long> = BehaviorRelay.create()
    // Disposable
    private var timerDisposable: Disposable? = null

    init {
        fetchCoinDetailRelay
            .flatMap { coindId ->
                fetchCoinDetail(coindId)
            }.subscribeBy { coinDetailItemModel ->
                coinDetailSubject.onNext(coinDetailItemModel)
            }.disposed(by = disposeBag)

        coinRefreshingIntervalRelay
            .subscribeBy { interval ->
                startCoinRefreshing()
            }.disposed(by = disposeBag)
    }

    private fun fetchCoinDetail(coindId: String?): Observable<CoinDetailItemModel> {
        return fetchCoinDetailUseCase(FetchCoinDetailUseCase.Params(coindId))
            .compose(activityIndicator.trackActivitySingle())
            .toObservable()
            .compose(error.trackError())
            .onErrorResumeNext { error ->
                Log.d("ERROR:", error.toString())
                Observable.empty()
            }.doFinally { stopRefreshLayout.onNext(Unit) }
    }

    private fun startCoinRefreshing() {
        coinRefreshingIntervalRelay.value?.let { interval ->
            timerDisposable?.dispose()
            timerDisposable = Observable.create<String> { emitter ->
                fetchCoinDetailRelay.accept(fetchCoinDetailRelay.value)
                emitter.onComplete()
            }.timer(interval)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .disposed(by = disposeBag)
        }
    }

    fun stopCoinRefreshing() {
        timerDisposable?.dispose()
    }
}
