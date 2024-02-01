package com.synrgy.wefly.ui.transaction

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.transaction.Passenger
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
        }
    }

    private fun updateRecyclerView(list: List<Passenger>) {
        with(binding) {
            val layoutManager = LinearLayoutManager(context)
            val recView = rvPassenger
            val adapter = PassengerAdapter(listItem = list, object : PassengerAdapter.PassengerListener {
                override fun onItemClick(item: Passenger) {

                }
            })
            recView.layoutManager = layoutManager
            recView.adapter = adapter
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
                            val data = it.data?.data?.passengers
                            Log.d("neotica", "observeStateFlow: $data")
                            it.data?.data?.passengers?.let { it1 -> updateRecyclerView(list = it1) }

                            btnOrder.setOnClickListener {}
                        }
                    }
                    is ApiResult.Error -> binding.pbMain.visibility = View.GONE
                }
            }
        }
    }

}
