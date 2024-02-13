package com.synrgy.wefly.ui.forgotpassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.databinding.FragmentForgotPassBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_pass) {
    private var _binding: FragmentForgotPassBinding? = null
    private val binding: FragmentForgotPassBinding get() = _binding!!


    private val viewModel: ForgotPassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentForgotPassBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            btnReset.setOnClickListener {
                observeStateFlow()
                val forgotPassString = ForgotPassRequest(email = etEmail.text.toString())
                viewModel.forgotPass(forgotPassString)
            }
            tvBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            with(binding) {
                viewModel.password.collect {
                    when(it) {
                        is ApiResult.Loading -> pbMain.visibility = View.VISIBLE
                        is ApiResult.Success -> {
                            pbMain.visibility = View.GONE
                            Toast.makeText(context, "Success: ${it.data?.message}", Toast.LENGTH_SHORT).show()
                            val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToForgotPasswordOtpFragment()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
