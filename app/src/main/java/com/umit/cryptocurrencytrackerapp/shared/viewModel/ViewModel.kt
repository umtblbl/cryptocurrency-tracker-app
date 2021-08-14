package com.umit.cryptocurrencytrackerapp.shared.viewModel

import com.umit.cryptocurrencytrackerapp.shared.utilities.ErrorTracker
import com.umit.cryptocurrencytrackerapp.shared.utilities.RxActivityIndicator
import io.reactivex.rxjava3.disposables.CompositeDisposable
import androidx.lifecycle.ViewModel as VM

abstract class ViewModel : VM() {

    protected val disposeBag: CompositeDisposable = CompositeDisposable()
    val activityIndicator: RxActivityIndicator = RxActivityIndicator()
    val error: ErrorTracker = ErrorTracker()

    public override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    fun clearSubscriptions() {
        disposeBag.clear()
    }
}
