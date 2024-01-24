package com.synrgy.wefly.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
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
        repeatCollectionOnCreated {
            tokenRan()
        }
    }

    private fun setupUI() {
        with(binding) {
            btnSearchFlight.setOnClickListener { viewModel.getAirportList();observeStateFlow() }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.airportList.collect {
                val content = it.data?.data?.content?.get(1)?.city
                when(it){
                    is ApiResult.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    is ApiResult.Success -> Toast.makeText(context, content.toString(), Toast.LENGTH_SHORT).show()
                    is ApiResult.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun handleSignInResult(status: ApiResult<LoginResponse>) {
        with(binding) {

        }
    }
    private suspend fun tokenRan () {
        viewModel.token.collect {
            if (it.isEmpty()) {
                gotoLogin()
            }
            Log.d("neotica", "token: $it")
        }
    }

    private fun gotoLogin() {
        val action = HomepageFragmentDirections.actionHomepageFragmentToAuthGroup()
        findNavController().navigate(action)
    }
}
