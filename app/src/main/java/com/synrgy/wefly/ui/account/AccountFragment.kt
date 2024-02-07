package com.synrgy.wefly.ui.account

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding

    private val viewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)

        setupUI()
        repeatCollectionOnCreated { tokenRan() }
    }

    private fun setupUI() {
        with(binding) {
            btnLogout.setOnClickListener {
                val action = AccountFragmentDirections.actionAccountFragmentToAuthGroup()
                findNavController().navigate(action)
                viewModel.logout()
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

    private fun tokenRan () {
        if (viewModel.token.isEmpty()) {
              gotoLogin()
        }
        Log.d("neotica", "token: ${viewModel.token}")
    }

    private fun gotoLogin() {
        val action = AccountFragmentDirections.actionAccountFragmentToAuthGroup()
        findNavController().navigate(action)
    }
}
