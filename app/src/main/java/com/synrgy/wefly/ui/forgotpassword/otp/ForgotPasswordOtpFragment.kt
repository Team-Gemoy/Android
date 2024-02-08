package com.synrgy.wefly.ui.forgotpassword.otp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.databinding.FragmentForgotPassOtpBinding
import com.synrgy.wefly.ui.forgotpassword.ForgotPassViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordOtpFragment : Fragment(R.layout.fragment_forgot_pass_otp) {
    private lateinit var binding: FragmentForgotPassOtpBinding

    private val viewModel: ForgotPassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPassOtpBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            btnContinue.setOnClickListener {
                observeStateFlow()
                val pin = pinMain.text.toString()
                Log.d("neotica", "setupUI: $pin")
                viewModel.checkOtp(pin)
            }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            with(binding) {
                viewModel.otp.collect {
                    when(it) {
                        is ApiResult.Loading -> pbMain.visibility = View.VISIBLE
                        is ApiResult.Success -> {
                            pbMain.visibility = View.GONE
                            Toast.makeText(context, "Success: ${it.data?.message}", Toast.LENGTH_SHORT).show()
                            val action = ForgotPasswordOtpFragmentDirections.actionForgotPasswordOtpFragmentToChangePasswordFragment(pinMain.text.toString())
                            findNavController().navigate(action)
                        }
                        is ApiResult.Error -> {
                            pbMain.visibility = View.GONE
                            Toast.makeText(context, "Request Error: ${it.data?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

}
