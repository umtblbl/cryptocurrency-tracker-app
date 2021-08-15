package com.umit.cryptocurrencytrackerapp.scenes.favoriteCoinList

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentFavoriteCoinListBinding
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment

class FavoriteCoinListFragment(
    override val layoutResId: Int = R.layout.fragment_favorite_coin_list
) : BaseFragment<FavoriteCoinListViewModel, FragmentFavoriteCoinListBinding>(FavoriteCoinListViewModel::class) {

    override fun setupView() {
    }
}
