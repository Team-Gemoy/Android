package com.synrgy.wefly.ui.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.MainActivity
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
import com.synrgy.wefly.data.api.ApiResult
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


        repeatCollectionOnCreated { tokenRan() }

        observeStateFlow()
    }

    private fun setupUI() {
        with(binding) {
            btnLogout.setOnClickListener {
                val action = AccountFragmentDirections.actionAccountFragmentToAuthGroup()
                viewModel.logout()
                findNavController().popBackStack()
                findNavController().navigate(action)

            }
            tvTitle.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java)

                // Clear the activity stack and start the activity
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                // Finish the current fragment (optional)
                activity?.finish()
                Log.d("TAG", "setupUI: dfsfd")
            }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.profile.collect() { result ->
                when(result) {
                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        val data = result.data?.data
                        binding.apply {
                            tvFullName.text = data?.fullName
                            tvUsername.text = data?.username
                            tvGender.text = data?.gender
                            tvCity.text = data?.city
                            tvDateOfBirth.text = data?.dateOfBirth
                            tvPhoneNumber.text = data?.phoneNumber

                        }
                    }
                    is ApiResult.Error -> {}
                }
            }
        }
    }

    private fun tokenRan () {
        if (viewModel.token.isEmpty()) {
              gotoLogin()
        } else {
            setupUI()
            Log.d("neotica", viewModel.token)
            print(viewModel.token)
            viewModel.getProfile()
        }
     //   Log.d("neotica", "token: ${viewModel.token}")
    }

    private fun gotoLogin() {
        val action = AccountFragmentDirections.actionAccountFragmentToAuthGroup()
        findNavController().navigate(action)
     //   findNavController().popBackStack()
    }
}
