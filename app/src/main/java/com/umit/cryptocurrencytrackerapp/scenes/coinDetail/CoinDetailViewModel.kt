package com.umit.cryptocurrencytrackerapp.scenes.coinDetail

import com.jakewharton.rxrelay3.BehaviorRelay
import com.umit.cryptocurrencytrackerapp.domain.coins.FetchCoinDetailUseCase
import com.umit.cryptocurrencytrackerapp.domain.user.AddFavoriteCoinUseCase
import com.umit.cryptocurrencytrackerapp.domain.user.DeleteFavoriteCoinUseCase
import com.umit.cryptocurrencytrackerapp.domain.user.GetFavoriteCoinUseCase
import com.umit.cryptocurrencytrackerapp.scenes.coinDetail.model.CoinDetailItemModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.extensions.timer
import com.umit.cryptocurrencytrackerapp.shared.utilities.RxBus
import com.umit.cryptocurrencytrackerapp.shared.utilities.RxEvent
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val fetchCoinDetailUseCase: FetchCoinDetailUseCase,
    private val getFavoriteCoinUseCase: GetFavoriteCoinUseCase,
    private val addFavoriteCoinUseCase: AddFavoriteCoinUseCase,
    private val deleteFavoriteCoinUseCase: DeleteFavoriteCoinUseCase
) : ViewModel() {

    // RefreshLayout
    val stopRefreshLayout: PublishSubject<Unit> = PublishSubject.create()

    // Disposable
    private var timerDisposable: Disposable? = null

    // Action
    val favoriteAction: PublishSubject<Unit> = PublishSubject.create()

    // Data Call Subject
    val fetchCoinDetailRelay: BehaviorRelay<String> = BehaviorRelay.create()

    // Data
    val coinDetailRelay: BehaviorRelay<CoinDetailItemModel> = BehaviorRelay.create()
    val isFavoriteCoinRelay: BehaviorRelay<Boolean> = BehaviorRelay.createDefault(false)

    // Toast Relay
    val showToastRelay: PublishSubject<Boolean> = PublishSubject.create()

    // Timer
    val coinRefreshingIntervalRelay: BehaviorRelay<Long> = BehaviorRelay.create()

    init {
        favoriteAction
            .subscribeBy {
                if (isFavoriteCoinRelay.value)
                    deleteFavoriteCoin(coinDetailRelay.value.id)
                else
                    addFavoriteCoin(coinDetailRelay.value)
            }.disposed(by = disposeBag)

        fetchCoinDetailRelay
            .flatMap { coinId ->
                checkFavoriteCoin(coinId)
                fetchCoinDetail(coinId)
            }.subscribeBy { coinDetailItemModel ->
                coinDetailRelay.accept(coinDetailItemModel)
            }
            .disposed(by = disposeBag)

        coinRefreshingIntervalRelay
            .subscribeBy { interval ->
                startCoinRefreshing(interval)
            }.disposed(by = disposeBag)
    }

    private fun addFavoriteCoin(model: CoinDetailItemModel) {
        addFavoriteCoinUseCase(
            AddFavoriteCoinUseCase.Params(
                id = model.id,
                symbol = model.symbol,
                name = model.name
            )
        ).subscribeBy { isSuccess ->
            showToastRelay.onNext(isSuccess)
            if (isSuccess) {
                checkFavoriteCoin(model.id)
                RxBus.publish(RxEvent.RefreshFavoriteCoinList)
            }
        }.disposed(by = disposeBag)
    }

    private fun deleteFavoriteCoin(coinId: String?) {
        deleteFavoriteCoinUseCase(DeleteFavoriteCoinUseCase.Params(coinId))
            .subscribeBy { isSuccess ->
                showToastRelay.onNext(isSuccess)
                if (isSuccess) {
                    checkFavoriteCoin(coinId)
                    RxBus.publish(RxEvent.RefreshFavoriteCoinList)
                }
            }.disposed(by = disposeBag)
    }

    private fun checkFavoriteCoin(id: String?) {
        getFavoriteCoinUseCase(GetFavoriteCoinUseCase.Params(id))
            .subscribeBy {
                isFavoriteCoinRelay.accept(it.value != null)
            }.disposed(by = disposeBag)
    }

    private fun fetchCoinDetail(coinId: String?): Observable<CoinDetailItemModel> {
        return fetchCoinDetailUseCase(FetchCoinDetailUseCase.Params(coinId))
            .compose(activityIndicator.trackActivitySingle())
            .toObservable()
            .compose(error.trackError())
            .onErrorResumeNext {
                Observable.empty()
            }.doFinally { stopRefreshLayout.onNext(Unit) }
    }

    private fun startCoinRefreshing(interval: Long) {
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

    fun stopCoinRefreshing() {
        timerDisposable?.dispose()
    }
}
