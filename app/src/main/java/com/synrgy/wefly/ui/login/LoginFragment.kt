package com.synrgy.wefly.ui.login

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
import com.synrgy.wefly.data.api.json.login.LoginRequest
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
            tvRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
            btnLogin.setOnClickListener {
               /* val loginRequest = LoginRequest(
                    email = "laetuzg@gmail.com",
                    password = "Kingkong123!"
                )*/
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val loginRequest = LoginRequest(
                    email, password
                )
                viewModel.login(loginRequest)
                observeStateFlow()
            }
        }
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
                    pbMain.visibility = View.VISIBLE
                    Log.d("neotica", "loading")
                }

                is ApiResult.Error -> {
                    pbMain.visibility = View.GONE
                    val error = status.errorMessage
                    Log.d("neotica", "$error")
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Success -> {
                    pbMain.visibility = View.GONE
                    val head = status.data?.code.toString()
                    val getToken = status.data?.accessToken.toString()
                    viewModel.setToken(getToken)
                    gotoHome()
                    Log.d("neotica", "handleSignInResult: $head")
                    Log.d("neotica", "token success: $getToken")
                }
            }
        }
    }

    private fun gotoHome() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(500)
            findNavController().popBackStack()
            val action = LoginFragmentDirections.actionGlobalHomeFragment()
            findNavController().navigate(action)
        }
    }
}
