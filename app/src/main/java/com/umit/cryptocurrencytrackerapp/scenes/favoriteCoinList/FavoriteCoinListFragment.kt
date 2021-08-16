package com.umit.cryptocurrencytrackerapp.scenes.favoriteCoinList

import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.databinding.FragmentFavoriteCoinListBinding
import com.umit.cryptocurrencytrackerapp.scenes.home.HomeFragmentDirections
import com.umit.cryptocurrencytrackerapp.shared.extensions.action
import com.umit.cryptocurrencytrackerapp.shared.extensions.disposed
import com.umit.cryptocurrencytrackerapp.shared.fragment.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class FavoriteCoinListFragment(
    override val layoutResId: Int = R.layout.fragment_favorite_coin_list
) : BaseFragment<FavoriteCoinListViewModel, FragmentFavoriteCoinListBinding>(FavoriteCoinListViewModel::class) {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun setupView() {

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
