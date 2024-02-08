package com.synrgy.wefly.ui.order

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderFragment : Fragment(R.layout.fragment_order) {
    private lateinit var binding: FragmentOrderBinding

    private val viewModel: OrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)

        setupUI()
        repeatCollectionOnCreated { tokenRan() }
        viewModel.getHistory()
        observeStateFlow()
    }

    private fun setupUI() {
        with(binding) {

        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.history.collect {
                when(it) {
                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        it.data?.let { dataLol ->
                            updateRecyclerView(dataLol.data.content)
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
        }
        Log.d("neotica", "token: ${viewModel.token}")
    }

    private fun gotoLogin() {
        val action = OrderFragmentDirections.actionOrderFragmentToAuthGroup()
        findNavController().navigate(action)
    }

    private fun updateRecyclerView(list: List<TransactionListResponse>) {
        with(binding) {
            val layoutManager = LinearLayoutManager(context)
            val recView = rvOrder
            val adapter = OrderAdapter(listItem = list, object : OrderAdapter.OrderListener {
                override fun onItemClick(item: TransactionListResponse) {
                 /*   val action = FlightFragmentDirections.actionFlightFragmentToTransactionFragment()
                    findNavController().navigate(action)
                    Toast.makeText(context, "${item.flight.basePrice}", Toast.LENGTH_SHORT).show()*/
                }
            })
            recView.layoutManager = layoutManager
            recView.adapter = adapter
        }
    }
}
