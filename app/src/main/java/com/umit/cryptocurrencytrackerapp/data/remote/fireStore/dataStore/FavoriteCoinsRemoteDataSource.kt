package com.umit.cryptocurrencytrackerapp.data.remote.fireStore.dataStore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import com.umit.cryptocurrencytrackerapp.data.remote.fireStore.FireStoreDataType
import com.umit.cryptocurrencytrackerapp.data.remote.fireStore.FireStoreKeyType
import com.umit.cryptocurrencytrackerapp.data.remote.fireStore.reference
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.model.Optional
import com.umit.cryptocurrencytrackerapp.shared.model.toOptional
import java.util.Locale
import javax.inject.Inject

class FavoriteCoinsRemoteDataSource @Inject constructor() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun add(id: String, symbol: String, name: String, handler: (Boolean) -> Unit) {
        val params = hashMapOf<String, Any>().apply {
            put(FireStoreKeyType.Id.name, id)
            put(FireStoreKeyType.Symbol.name, symbol)
            put(FireStoreKeyType.Name.name, name)
        }
        firebaseAuth.currentUser?.uid?.let { userId ->
            FireStoreDataType.Favorite(userId)
                .reference()
                .add(params)
                .addOnSuccessListener {
                    handler(true)
                }
                .addOnFailureListener {
                    handler(false)
                }
        }
    }

    fun delete(id: String, handler: (Boolean) -> Unit) {
        search(id) {
            it.documents.forEach { document -> document.reference.delete() }
            handler(true)
        }
    }

    fun get(id: String, handler: (Optional<CoinItemModel>) -> Unit) {
        search(id) { querySnapshot ->
            querySnapshot.documents.firstOrNull()?.let {
                CoinItemModel(
                    id = it.get(FireStoreKeyType.Id.name).toString(),
                    symbol = "[${it.get(FireStoreKeyType.Symbol.name).toString().uppercase(Locale.getDefault())}]",
                    name = it.get(FireStoreKeyType.Name.name).toString(),
                    firstSymbolLetter = it.get(FireStoreKeyType.Symbol.name)
                        .toString()
                        .first().toString().uppercase(Locale.getDefault())
                ).also { model ->
                    handler(model.toOptional())
                }
            } ?: handler(Optional(null))
        }
    }

    fun getAll(handler: (List<CoinItemModel>) -> Unit) {
        firebaseAuth.currentUser?.uid?.let { userId ->
            FireStoreDataType.Favorite(userId)
                .reference()
                .get()
                .addOnSuccessListener { querySnapshot ->
                    querySnapshot.documents.map {
                        CoinItemModel(
                            id = it.get(FireStoreKeyType.Id.name).toString(),
                            symbol = it.get(FireStoreKeyType.Symbol.name).toString(),
                            name = it.get(FireStoreKeyType.Name.name).toString(),
                            firstSymbolLetter = it.get(FireStoreKeyType.Symbol.name)
                                .toString()
                                .first().toString().uppercase(Locale.getDefault())
                        )
                    }.also { models ->
                        handler(models)
                    }
                }
        }
    }

    private fun search(id: String, handler: (QuerySnapshot) -> Unit) {
        firebaseAuth.currentUser?.uid?.let { userId ->
            FireStoreDataType.Favorite(userId)
                .reference()
                .whereEqualTo(FireStoreKeyType.Id.name, id)
                .get()
                .addOnSuccessListener {
                    handler(it)
                }
        }
    }
}
