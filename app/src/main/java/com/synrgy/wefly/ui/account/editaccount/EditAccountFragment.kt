package com.synrgy.wefly.ui.account.editaccount

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
import com.synrgy.wefly.data.api.json.register.AccountRequest
import com.synrgy.wefly.databinding.FragmentAccountEditBinding
import com.synrgy.wefly.ui.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditAccountFragment : Fragment(R.layout.fragment_account_edit) {
    private var _binding: FragmentAccountEditBinding? = null
    private val binding: FragmentAccountEditBinding get() = _binding!!

    private val viewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAccountEditBinding.bind(view)


        repeatCollectionOnCreated { tokenRan() }

        observeStateFlow()
    }

    private fun setupUI() {
        with(binding) {
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
                when (result) {
                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        val data = result.data?.data
                        binding.apply {
                            etFullName.setText(data?.fullName)
                            etUsername.setText(data?.username)
                            etGender.setText(data?.gender)
                            etCity.setText(data?.city)
                            etDateOfBirth.setText(data?.dateOfBirth)
                            etPhoneNumber.setText(data?.phoneNumber)

                        }
                        binding.apply {
                            btnEdit.setOnClickListener {
                                val profileReq = AccountRequest(
                                    fullName = etFullName.text.toString(),
                                    email = etUsername.text.toString(),
                                    gender = etGender.text.toString(),
                                    city = etCity.text.toString(),
                                    dateOfBirth = etDateOfBirth.text.toString(),
                                    phoneNumber = etPhoneNumber.text.toString(),
                                )
                                viewModel.editProfile(profileReq)
                                findNavController().navigate(EditAccountFragmentDirections.actionEditAccountFragmentToAccountFragment())
                            }
                        }
                    }

                    is ApiResult.Error -> {}
                }
            }
        }
    }

    private fun tokenRan() {
        if (viewModel.token.isEmpty()) {
            //    gotoLogin()
        } else {
            setupUI()
            Log.d("neotica", viewModel.token)
            print(viewModel.token)
            viewModel.getProfile()
        }
        //   Log.d("neotica", "token: ${viewModel.token}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* private fun gotoLogin() {
         val action = AccountFragmentDirections.actionAccountFragmentToAuthGroup()
         findNavController().navigate(action)
      //   findNavController().popBackStack()
     }*/
}
