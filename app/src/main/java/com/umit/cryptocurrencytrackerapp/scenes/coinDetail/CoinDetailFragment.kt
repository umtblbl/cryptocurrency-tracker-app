package com.umit.cryptocurrencytrackerapp.scenes.coinDetail

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentCoinDetailBinding
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment

class CoinDetailFragment(
    override val layoutResId: Int = R.layout.fragment_coin_detail
) : BaseFragment<CoinDetailViewModel, FragmentCoinDetailBinding>(CoinDetailViewModel::class) {

    override fun setupView() {
    }
}
