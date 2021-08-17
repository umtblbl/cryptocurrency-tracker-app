package com.umit.cryptocurrencytrackerapp.data.remote.fireStore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

sealed class FireStoreDataType {
    class Favorite(val userId: String) : FireStoreDataType()
}

fun FireStoreDataType.reference(): CollectionReference {
    return when (this) {
        is FireStoreDataType.Favorite -> {
            FirebaseFirestore.getInstance()
                .collection("app")
                .document("users")
                .collection(userId)
        }
    }
}
