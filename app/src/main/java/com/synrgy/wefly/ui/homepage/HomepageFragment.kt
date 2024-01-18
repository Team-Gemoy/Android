package com.synrgy.wefly.ui.homepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentHomepageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomepageFragment : Fragment(R.layout.fragment_homepage) {
    private lateinit var binding: FragmentHomepageBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomepageBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        with(binding) {

        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {

        }
    }

    private fun handleSignInResult(status: ApiResult<LoginResponse>) {
        with(binding) {

        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
       /* val currentUser = auth.currentUser f
        if (currentUser == null) {
            gotoLogin()
        }*/
        gotoLogin()
    }

    private fun gotoLogin() {
        val action = HomepageFragmentDirections.actionHomepageFragmentToAuthGroup()
        findNavController().navigate(action)
    }
}
