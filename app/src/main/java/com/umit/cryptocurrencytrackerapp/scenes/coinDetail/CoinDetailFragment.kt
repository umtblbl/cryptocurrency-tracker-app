package com.umit.cryptocurrencytrackerapp.scenes.coinDetail

import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentCoinDetailBinding
import com.umit.cryptocurrencytrackerapp.scenes.coinDetail.view.RefreshIntervalDialog
import com.umit.cryptocurrencytrackerapp.shared.dialog.AppToast
import com.umit.cryptocurrencytrackerapp.shared.dialog.ToastType
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.changes
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class CoinDetailFragment(
    override val layoutResId: Int = R.layout.fragment_coin_detail
) : BaseFragment<CoinDetailViewModel, FragmentCoinDetailBinding>(CoinDetailViewModel::class) {

    private val args: CoinDetailFragmentArgs by navArgs()
    private val refreshIntervalDialog by lazy { RefreshIntervalDialog(requireContext()) }
    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val errorToast by lazy { AppToast(activity = activity, type = ToastType.ProcessFailed) }
    private val successToast by lazy { AppToast(activity = activity, type = ToastType.ProcessSuccessful) }

    override fun setupView() {
        binding.backImageView.action().subscribe(super.backPressSubject)
        viewModel.fetchCoinDetailRelay.accept(args.coinId)

        binding.favoriteImageView
            .action()
            .subscribeBy {
            }.disposed(by = disposeBag)

        binding.refreshIntervalImageView
            .action()
            .subscribeBy {
                refreshIntervalDialog.show()
            }.disposed(by = disposeBag)

        viewModel.coinDetailRelay
            .subscribeBy { coinDetailModel ->
                binding.container.visibility = View.VISIBLE
                binding.model = coinDetailModel
            }.disposed(by = disposeBag)

        refreshIntervalDialog
            .typeAction
            .subscribeBy { intervalType ->
                viewModel.coinRefreshingIntervalRelay.accept(intervalType.value)
            }.disposed(by = disposeBag)

        binding.swipeRefreshLayout
            .changes()
            .subscribeBy {
                viewModel.fetchCoinDetailRelay.accept(args.coinId)
            }.disposed(by = disposeBag)

        viewModel.stopRefreshLayout
            .subscribeBy {
                binding.swipeRefreshLayout.isRefreshing = false
            }.disposed(by = disposeBag)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopCoinRefreshing()
    }

    override fun onResume() {
        super.onResume()
        viewModel.coinRefreshingIntervalRelay.value?.let {
            viewModel.coinRefreshingIntervalRelay.accept(it)
        }
    }
}
