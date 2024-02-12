package com.synrgy.wefly.ui.payment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentPaymentSuccessBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentSuccessFragment : Fragment(R.layout.fragment_payment_success) {
    private var _binding: FragmentPaymentSuccessBinding? = null
    private val binding: FragmentPaymentSuccessBinding get() = _binding!!

   // private val viewModel: OrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaymentSuccessBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            btnHome.setOnClickListener {
                findNavController().navigate(PaymentSuccessFragmentDirections.actionPaymentSuccessFragmentToHomepageFragment())
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
