package com.umit.cryptocurrencytrackerapp.scenes.coinList

import android.os.Build
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentCoinListBinding
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.scenes.home.HomeFragmentDirections
import com.umit.cryptocurrencytrackerapp.shared.adapter.RecyclerViewBasicAdapter
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.changes
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.extensions.showKeyboard
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class CoinListFragment(
    override val layoutResId: Int = R.layout.fragment_coin_list
) : BaseFragment<CoinListViewModel, FragmentCoinListBinding>(CoinListViewModel::class) {

    private var adapter: RecyclerViewBasicAdapter<CoinItemModel>
        get() = binding.coinRecyclerView.adapter as RecyclerViewBasicAdapter<CoinItemModel>
        set(value) {
            binding.coinRecyclerView.adapter = value
        }

    override fun setupView() {

        binding.coinSearchView
            .changes()
            .subscribeBy {
                if (it.isSubmit) binding.coinSearchView.clearFocus()
                viewModel.fetchSearchedCoinsSubject.onNext(it.text)
            }.disposed(by = disposeBag)

        binding.swipeRefreshLayout
            .changes()
            .subscribeBy {
                viewModel.fetchCoinsRelay.accept(Unit)
            }.disposed(by = disposeBag)

        viewModel.stopRefreshLayout
            .subscribeBy {
                binding.swipeRefreshLayout.isRefreshing = false
            }.disposed(by = disposeBag)

        customizeSearchView()
        initCoinRecyclerView()
    }

    private fun initCoinRecyclerView() {
        adapter = RecyclerViewBasicAdapter(layoutId = R.layout.item_coin)
        viewModel.coinListRelay.subscribeBy { adapter.list = it }.disposed(by = disposeBag)

        adapter.modelSelected
            .subscribeBy { coinModel ->
                coinModel.id?.let { coinId ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment(coinId)
                    )
                }
            }.disposed(by = disposeBag)
    }

    private fun customizeSearchView() {
        binding.coinSearchView.findViewById<EditText>(R.id.search_src_text).apply {
            ContextCompat.getColor(requireContext(), R.color.white).let { color ->
                setTextColor(color)
                setHintTextColor(color)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setTextCursorDrawable(R.drawable.edittext_cursor_bg)
            }

            binding.coinSearchView
                .action()
                .subscribeBy {
                    binding.coinSearchView.isIconified = false
                    context.showKeyboard(this)
                }.disposed(by = disposeBag)
        }
    }
}
