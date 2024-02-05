package com.synrgy.wefly.ui.flight

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.wefly.R
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.flight.FlightContent
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
    }

    private fun setupUI() {
        val args = FlightFragmentArgs.fromBundle(arguments as Bundle)
        with(binding) {
            viewModel.getFlight(
                departDate = "26-01-2024",
                seatClass = args.seatClass,
                numberOfPassenger = args.passenger,
                departureAirportId = 2,
                arrivalAirportId = 1
            )
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flightList.collect() { result ->
                when(result) {
                    is ApiResult.Loading -> binding.pbMain.visibility = View.VISIBLE
                    is ApiResult.Success -> {
                        binding.pbMain.visibility = View.GONE
                        result.data?.let {
                            if (it.data.content.isEmpty()) {
                                Log.d("flight", "observeStateFlow: error")
                            } else {
                                updateRecyclerView(it.data.content)
                            }
                        }
                    }
                    is ApiResult.Error -> {
                        binding.pbMain.visibility = View.GONE
                        Log.d("flight", "observeStateFlow: error")
                    }
                }
            }
        }
    }

    private fun updateRecyclerView(list: List<FlightContent>) {
        val binding = view?.let { FragmentFlightBinding.bind(it) }
        val layoutManager = LinearLayoutManager(context)
        val recView = binding?.rvFlight
        val adapter = FlightAdapter(listItem = list, object : FlightAdapter.FlightListener {
            override fun onItemClick(item: FlightContent) {
                val action = FlightFragmentDirections.actionFlightFragmentToTransactionFragment()
                findNavController().navigate(action)
                Toast.makeText(context, "${item.flight.basePrice}", Toast.LENGTH_SHORT).show()
            }
        })
        recView?.layoutManager = layoutManager
        recView?.adapter = adapter
    }
}
