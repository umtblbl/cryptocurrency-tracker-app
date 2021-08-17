package com.umit.cryptocurrencytrackerapp.scenes.favoriteCoinList

import com.jakewharton.rxrelay3.BehaviorRelay
import com.umit.cryptocurrencytrackerapp.domain.user.GetAllFavoriteCoinUseCase
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.utilities.RxBus
import com.umit.cryptocurrencytrackerapp.shared.utilities.RxEvent
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class FavoriteCoinListViewModel @Inject constructor(
    private val getAllFavoriteCoinUseCase: GetAllFavoriteCoinUseCase
) : ViewModel() {

    // RefreshLayout
    val stopRefreshLayout: PublishSubject<Unit> = PublishSubject.create()

    // Data Fetch Subject
    val fetchFavoriteCoinsRelay: BehaviorRelay<Unit> = BehaviorRelay.createDefault(Unit)

    // Data
    val favoriteCoinsRelay: BehaviorRelay<List<CoinItemModel>> = BehaviorRelay.create()

    init {
        fetchFavoriteCoinsRelay
            .flatMap {
                getAllFavoriteCoinUseCase()
            }
            .subscribeBy { coinsList ->
                favoriteCoinsRelay.accept(coinsList)
                stopRefreshLayout.onNext(Unit)
            }.disposed(by = disposeBag)

        RxBus.listen(RxEvent.RefreshFavoriteCoinList::class.java)
            .subscribeBy {
                fetchFavoriteCoinsRelay.accept(Unit)
            }.disposed(by = changesDisposeBag)
    }
}
