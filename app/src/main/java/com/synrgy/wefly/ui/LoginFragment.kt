package com.synrgy.wefly.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        with(binding) {

        }
        val loginRequest = LoginRequest(
            email = "laetuzg@gmail.com",
            password = "Kingkong123!"
        )

        viewModel.login(loginRequest)
        observeStateFlow()
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginStateFlow.collect {
                handleSignInResult(it)
            }
        }
    }

    private fun handleSignInResult(status: ApiResult<LoginResponse>) {
        with(binding) {
            when (status) {
                is ApiResult.Loading -> {
                   // pbLogin.visibility = View.VISIBLE
                    Log.d("neotica", "loading")
                }

                is ApiResult.Error -> {
                  //  pbLogin.visibility = View.GONE
                    val error = status.errorMessage
                    Log.d("neotica", "$error")
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Success -> {
                 //   pbLogin.visibility = View.GONE
                    val head = status.data?.code.toString()
                    Log.d("neotica", "handleSignInResult: $head")
                }
            }
        }
    }
}
