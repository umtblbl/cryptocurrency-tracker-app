package com.umit.cryptocurrencytrackerapp.scenes.home

import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentHomeBinding
import com.umit.cryptocurrencytrackerapp.scenes.coinList.CoinListFragment
import com.umit.cryptocurrencytrackerapp.scenes.favoriteCoinList.FavoriteCoinListFragment
import com.umit.cryptocurrencytrackerapp.scenes.home.adapter.ViewPager2Adapter
import com.umit.cryptocurrencytrackerapp.shared.extensions.changes
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class HomeFragment(
    override val layoutResId: Int = R.layout.fragment_home
) : BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class) {

    override fun setupView() {
        activity?.supportFragmentManager?.let { supportFragmentManager ->
            ViewPager2Adapter(
                fragments = listOf(CoinListFragment(), FavoriteCoinListFragment()),
                fragmentManager = supportFragmentManager,
                lifecycle = lifecycle
            ).also { binding.viewPager2.adapter = it }
        }

        binding.tabLayout
            .changes()
            .subscribeBy {
                binding.viewPager2.currentItem = it
            }.disposed(by = disposeBag)

        binding.viewPager2
            .changes()
            .subscribeBy {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(it))
            }.disposed(by = disposeBag)
    }
}
