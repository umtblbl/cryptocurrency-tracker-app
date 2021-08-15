package com.umit.cryptocurrencytrackerapp.scenes.coinList

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentCoinListBinding
import com.umit.cryptocurrencytrackerapp.scenes.coinList.model.CoinItemModel
import com.umit.cryptocurrencytrackerapp.shared.adapter.RecyclerViewBasicAdapter
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class CoinListFragment(
    override val layoutResId: Int = R.layout.fragment_coin_list
) : BaseFragment<CoinListViewModel, FragmentCoinListBinding>(CoinListViewModel::class) {

    var adapter: RecyclerViewBasicAdapter<CoinItemModel>
        get() = binding.coinRecyclerView.adapter as RecyclerViewBasicAdapter<CoinItemModel>
        set(value) {
            binding.coinRecyclerView.adapter = value
        }

    override fun setupView() {
        viewModel.getCoin()
        initCoinRecyclerView()
    }

    private fun initCoinRecyclerView() {
        adapter = RecyclerViewBasicAdapter(layoutId = R.layout.item_coin)
        viewModel.coinListRelay.subscribeBy { adapter.list = it }.disposed(by = disposeBag)

        adapter.modelSelected
            .subscribeBy { coinModel ->
                println(coinModel.name)
            }.disposed(by = disposeBag)
    }
}
