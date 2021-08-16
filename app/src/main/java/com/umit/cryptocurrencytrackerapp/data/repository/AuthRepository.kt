package com.umit.cryptocurrencytrackerapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AuthRepository @Inject constructor() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun login(email: String?, password: String?): Observable<Boolean> {
        if (email.isNullOrBlank() || password.isNullOrBlank()) return Observable.empty()

        return Observable.create { emitter ->
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                emitter.onNext(it.isSuccessful)
            }
        }
    }

    fun register(email: String?, password: String?): Observable<Boolean> {
        if (email.isNullOrBlank() || password.isNullOrBlank()) return Observable.empty()

        return Observable.create { emitter ->
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                emitter.onNext(it.isSuccessful)
            }
        }
    }
}
