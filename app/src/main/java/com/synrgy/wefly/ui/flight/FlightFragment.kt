package com.synrgy.wefly.ui.flight

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.flight.FlightContent
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.databinding.FragmentFlightBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FlightFragment : Fragment(R.layout.fragment_flight) {
    private lateinit var binding: FragmentFlightBinding

    private val viewModel: FlightViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlightBinding.bind(view)

        setupUI()
        observeStateFlow()
        viewModel.getFlight()
    }

    private fun setupUI() {
        with(binding) {

        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flightList.collect() { result ->
                when(result) {
                    is ApiResult.Loading -> Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                    is ApiResult.Success -> {
                        result.data?.let {
                            if (it.data.content.isEmpty()) {
                                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
                            } else {
                                updateRecyclerView(it.data.content)
                            }
                        }
                    }
                    is ApiResult.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateRecyclerView(list: List<FlightContent>) {
        val binding = view?.let { FragmentFlightBinding.bind(it) }
        val layoutManager = LinearLayoutManager(context)
        val recView = binding?.rvFlight
        val adapter = FlightAdapter(listItem = list)
        recView?.layoutManager = layoutManager
        recView?.adapter = adapter
    }
}