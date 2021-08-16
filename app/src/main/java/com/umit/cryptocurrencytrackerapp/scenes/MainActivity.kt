package com.umit.cryptocurrencytrackerapp.scenes

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.umit.cryptocurrencytrackerapp.R
import com.umit.cryptocurrencytrackerapp.scenes.home.HomeFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if (::firebaseAuth.isInitialized) {
            firebaseAuth.currentUser ?: run {
                findViewById<FragmentContainerView>(R.id.fragmentContainerView)
                    .findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        }
    }
}
