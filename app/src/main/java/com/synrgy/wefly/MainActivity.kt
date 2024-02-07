package com.synrgy.wefly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.synrgy.wefly.databinding.ActivityMainBinding
import com.synrgy.wefly.ui.homepage.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_main_holder) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavView = binding.bottomNavigationView

        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Check if the current destination should hide the bottom navigation bar

            val isBottomNavVisible = when (destination.id) {
                R.id.loginFragment,
                R.id.registerFragment -> false // Replace with your fragment IDs
                else -> true
                /*
                if (viewModel.token.isEmpty()) {
                    //  gotoLogin()
                    when (destination.id) {
                        R.id.flightFragment,
                        R.id.homepageFragment,
                        R.id.loginFragment,
                        R.id.registerFragment -> false // Replace with your fragment IDs
                        else -> true
                    }
                } else {

                }*/
            }
            bottomNavView.visibility = if (isBottomNavVisible) View.VISIBLE else View.GONE
        }
    }

    private fun tokenRan () {
        if (viewModel.token.isEmpty()) {
            //  gotoLogin()
        }
        Log.d("neotica", "token: ${viewModel.token}")
    }
}