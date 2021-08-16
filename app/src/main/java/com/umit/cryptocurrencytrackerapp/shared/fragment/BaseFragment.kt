package com.umit.cryptocurrencytrackerapp.shared.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxrelay3.BehaviorRelay
import com.tapadoo.alerter.Alerter
import com.umit.cryptocurrencytrackerapp.di.Injectable
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.view.AppProgress
import com.umit.cryptocurrencytrackerapp.shared.viewModel.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<VM : ViewModel, Binding : ViewDataBinding>(
    private val clazz: KClass<VM>
) : Fragment(), FragmentType<VM, Binding>, Injectable {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override lateinit var binding: Binding
    override val disposeBag = CompositeDisposable()
    override val viewModel by lazy { ViewModelProvider(this, viewModelProviderFactory).get(clazz.java) }

    private val appProgress by lazy { AppProgress(requireContext()) }
    private val loadingRelay: BehaviorRelay<Boolean> = BehaviorRelay.createDefault(false)

    protected val backPressSubject: PublishSubject<Unit> = PublishSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        viewModel.activityIndicator.subscribe(loadingRelay).disposed(by = disposeBag)
        listenLoading()
        listenError()
        listenBackPress()
        setupView()
        return binding.root
    }

    private fun listenBackPress() {
        backPressSubject
            .subscribeBy {
                findNavController().popBackStack()
            }.disposed(by = disposeBag)
    }

    private fun listenError() {
        viewModel.error.subscribeBy {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
            activity?.let { activity ->
                Alerter.create(activity)
                    .setText(it.localizedMessage?.toString() ?: "unknown error")
                    .setDuration(3000)
                    .show()
            }
        }.disposed(by = disposeBag)
    }

    private fun listenLoading() {
        loadingRelay.subscribeBy(
            onNext = { show ->
                if (show) appProgress.show()
                else appProgress.dismiss()
            }
        ).disposed(by = disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearSubscriptions()
        disposeBag.clear()
    }
}
