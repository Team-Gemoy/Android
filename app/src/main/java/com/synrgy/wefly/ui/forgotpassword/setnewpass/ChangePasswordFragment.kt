package com.synrgy.wefly.ui.forgotpassword.setnewpass

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.forgetpassword.changepassword.ChangePasswordRequest
import com.synrgy.wefly.databinding.FragmentChangePassBinding
import com.synrgy.wefly.ui.forgotpassword.ForgotPassViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePasswordFragment : Fragment(R.layout.fragment_change_pass) {
    private lateinit var binding: FragmentChangePassBinding

    private val viewModel: ForgotPassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangePassBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            btnReset.setOnClickListener {
                observeStateFlow()
                val args = ChangePasswordFragmentArgs.fromBundle(arguments as Bundle)
                val changePassBody = ChangePasswordRequest(
                    email = etEmail.text.toString(),
                    newPassword = etPassword.text.toString(),
                    confirmPassword = etPassword.text.toString(),
                    otp = args.otp
                )
                viewModel.changePass(changePassBody)
            }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            with(binding) {
                viewModel.newPass.collect {
                    when(it) {
                        is ApiResult.Loading -> pbMain.visibility = View.VISIBLE
                        is ApiResult.Success -> {
                            pbMain.visibility = View.GONE
                            Toast.makeText(context, "Success: ${it.data?.message}", Toast.LENGTH_SHORT).show()
                          /*  val action = ForgotPasswordOtpFragmentDirections.actionForgotPasswordOtpFragmentToChangePasswordFragment(pinMain.text.toString())
                            findNavController().navigate(action)*/
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
