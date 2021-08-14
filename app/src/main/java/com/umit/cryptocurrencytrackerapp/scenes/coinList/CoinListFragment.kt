package com.umit.cryptocurrencytrackerapp.scenes.coinList

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentCoinListBinding
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment

class CoinListFragment : BaseFragment<CoinListViewModel, FragmentCoinListBinding>(CoinListViewModel::class) {

    override fun layoutResourceId(): Int {
        return R.layout.fragment_coin_list
    }

    override fun setupView() {
    }
}
