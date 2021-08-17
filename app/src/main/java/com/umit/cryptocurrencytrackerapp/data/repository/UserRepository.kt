package com.umit.cryptocurrencytrackerapp.data.repository

import com.umit.cryptocurrencytrackerapp.data.remote.fireStore.dataSource.FavoriteCoinsRemoteDataSource
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.model.Optional
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val favoriteCoinsRemoteDataSource: FavoriteCoinsRemoteDataSource
) {

    fun getFavoriteCoin(id: String?): Observable<Optional<CoinItemModel>> {
        if (id.isNullOrBlank()) {
            return Observable.empty()
        }

        return Observable.create { emitter ->
            favoriteCoinsRemoteDataSource.get(id) {
                emitter.onNext(it)
            }
        }
    }

    fun addFavoriteCoin(id: String?, symbol: String?, name: String?): Observable<Boolean> {
        if (id.isNullOrBlank() || symbol.isNullOrBlank() || name.isNullOrBlank()) {
            return Observable.empty()
        }

        return Observable.create { emitter ->
            favoriteCoinsRemoteDataSource.add(
                id = id,
                symbol = symbol,
                name = name
            ) {
                emitter.onNext(it)
            }
        }
    }

    fun deleteFavoriteCoin(id: String?): Observable<Boolean> {
        if (id.isNullOrBlank()) {
            return Observable.empty()
        }

        return Observable.create { emitter ->
            favoriteCoinsRemoteDataSource.delete(id) {
                emitter.onNext(it)
            }
        }
    }

    fun getAllFavoriteCoin(): Observable<List<CoinItemModel>> {
        return Observable.create { emitter ->
            favoriteCoinsRemoteDataSource.getAll {
                emitter.onNext(it)
            }
        }
    }
}
