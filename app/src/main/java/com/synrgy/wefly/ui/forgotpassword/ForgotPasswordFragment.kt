package com.synrgy.wefly.ui.forgotpassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentForgotPassBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_pass) {
    private lateinit var binding: FragmentForgotPassBinding

   // private val viewModel: OrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPassBinding.bind(view)

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
}
