package com.umit.cryptocurrencytrackerapp.scenes.favoriteCoinList

import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentFavoriteCoinListBinding
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.scenes.home.HomeFragmentDirections
import com.umit.cryptocurrencytrackerapp.shared.adapter.RecyclerViewBasicAdapter
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.changes
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class FavoriteCoinListFragment(
    override val layoutResId: Int = R.layout.fragment_favorite_coin_list
) : BaseFragment<FavoriteCoinListViewModel, FragmentFavoriteCoinListBinding>(FavoriteCoinListViewModel::class) {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    private var adapter: RecyclerViewBasicAdapter<CoinItemModel>
        get() = binding.coinRecyclerView.adapter as RecyclerViewBasicAdapter<CoinItemModel>
        set(value) {
            binding.coinRecyclerView.adapter = value
        }

    override fun setupView() {
        adapter = RecyclerViewBasicAdapter(layoutId = R.layout.item_coin)

        viewModel.favoriteCoinsRelay.subscribeBy {
            adapter.list = it
            binding.coinRecyclerView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding.emptyListTextView.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }.disposed(by = disposeBag)

        adapter.modelSelected
            .subscribeBy { coinModel ->
                coinModel.id?.let { coinId ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment(coinId)
                    )
                }
            }.disposed(by = disposeBag)

        binding.swipeRefreshLayout
            .changes()
            .subscribeBy {
                viewModel.fetchFavoriteCoinsRelay.accept(Unit)
            }.disposed(by = disposeBag)

        viewModel.stopRefreshLayout
            .subscribeBy {
                binding.swipeRefreshLayout.isRefreshing = false
            }.disposed(by = disposeBag)

        binding.signOutImageView
            .action()
            .subscribeBy {
                firebaseAuth.signOut()
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                )
            }.disposed(by = disposeBag)
    }
}
