package com.synrgy.wefly.ui.transaction

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.synrgy.wefly.R
import com.synrgy.wefly.common.showDatePickerDialog
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.databinding.FragmentTransactionResponseBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionFragmentResponse : Fragment(R.layout.fragment_transaction_response) {
    private lateinit var binding: FragmentTransactionResponseBinding

    private val viewModel: TransactionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionResponseBinding.bind(view)

        setupUI()
        observeStateFlow()
    }

    private fun setupUI() {
        with(binding) {
            val args = TransactionFragmentResponseArgs.fromBundle(arguments as Bundle)
            viewModel.getTransaction(args.transactionId)
            /*etFirstName.setText(args.transactionId)
            etLastName.setText("ddsfs")*/


            etDateOfBirthPassenger.setOnClickListener {
                showDatePickerDialog(requireContext(), etDateOfBirthPassenger)
            }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getId.collect {
                when(it) {
                    is ApiResult.Loading -> binding.pbMain.visibility = View.VISIBLE
                    is ApiResult.Success -> {
                        with(binding){
                            pbMain.visibility = View.GONE
                            val data = it.data?.data?.passengers?.get(0)?.firstName
                            Log.d("neotica", "observeStateFlow: $data")
                            etFirstName.setText(data)
                            etLastName.setText("ddsfs")
                            btnOrder.setOnClickListener {}
                        }
                    }
                    is ApiResult.Error -> binding.pbMain.visibility = View.GONE
                }
            }
        }
    }

}
