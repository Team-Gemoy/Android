package com.synrgy.wefly.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.common.UiUtils.getTextInputLayout
import com.synrgy.wefly.common.showDatePickerDialog
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.register.RegisterRequest
import com.synrgy.wefly.data.api.json.register.RegisterResponse
import com.synrgy.wefly.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Created by Yosua on 18/01/2024
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        handleValidation()
        binding.apply {
            btnSignUp.setOnClickListener {
                observeStateFlow()
                val checkInputNoError = etEmail.getTextInputLayout().error == null &&
                        etFullname.getTextInputLayout().error == null &&
                        etPhoneNumber.getTextInputLayout().error == null &&
                        etPassword.getTextInputLayout().error == null

                if (checkInputNoError) {
                    val registerRequest = RegisterRequest(
                        email = etEmail.text.toString(),
                        fullName = etFullname.text.toString(),
                        phoneNumber = etPhoneNumber.text.toString(),
                        password = etPassword.text.toString(),
                        dateOfBirth = etDateOfBirth.text.toString()
                    )

                    viewModel.register(registerRequest)
                }
            }
            etDateOfBirth.setOnClickListener {
                // Open Date Picker
                showDatePickerDialog(requireContext(), etDateOfBirth)
            }
        }
    }

    private fun handleValidation() {
        binding.apply {
            etEmail.addTextChangedListener {
                if (it.toString().isEmpty()) {
                    binding.etEmail.getTextInputLayout().error =
                        resources.getString(R.string.email_required)
                } else {
                    binding.etEmail.getTextInputLayout().error = null
                }
            }

            etFullname.addTextChangedListener {
                if (it.toString().isEmpty()) {
                    binding.etFullname.getTextInputLayout().error =
                        resources.getString(R.string.name_required)
                } else {
                    binding.etFullname.getTextInputLayout().error = null
                }
            }

            etPhoneNumber.addTextChangedListener {
                if (it.toString().isEmpty()) {
                    binding.etPhoneNumber.getTextInputLayout().error =
                        resources.getString(R.string.phone_required)
                } else {
                    binding.etPhoneNumber.getTextInputLayout().error = null
                }
            }

            etPassword.addTextChangedListener {
                val password = it.toString()
             //   val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d).{8,}\$"

                if (password.isEmpty()) {
                    binding.etPassword.getTextInputLayout().error =
                        resources.getString(R.string.password_required)
                } /*else if (password.matches(passwordPattern.toRegex())) {
                    binding.etPassword.getTextInputLayout().error =
                        resources.getString(R.string.password_spec)
                }*/ else {
                    binding.etPassword.getTextInputLayout().error = null
                }
            }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.registerStateFlow.collect {
                handleRegisterResult(it)
            }
        }
    }

    private fun handleRegisterResult(status: ApiResult<RegisterResponse>) {
        when (status) {
            is ApiResult.Loading -> {
                binding.pbMain.visibility = View.VISIBLE
                Log.d("Register", "Loading")
            }

            is ApiResult.Error -> {
                binding.pbMain.visibility = View.GONE
                //Toast.makeText(requireContext(), status.errorMessage, Toast.LENGTH_SHORT).show()
                Log.d("Register", "Error")
                Toast.makeText(context, "Registration Error: ${status.data?.message}", Toast.LENGTH_SHORT).show()

            }

            is ApiResult.Success -> {
                binding.pbMain.visibility = View.GONE
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                findNavController().navigate(action)
                Log.d("Register", "Success")
                Toast.makeText(context, "Registration ${status.data?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}