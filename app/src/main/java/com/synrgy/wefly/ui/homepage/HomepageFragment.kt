package com.synrgy.wefly.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
import com.synrgy.wefly.common.showDatePickerDialog
import com.synrgy.wefly.common.spinnerAdapter
import com.synrgy.wefly.data.api.ApiResult
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
        viewModel.getAirportList()
        observeStateFlow()
    }

    private fun setupUI() {
        with(binding) {
            ivNotification.setOnClickListener {
                val action = HomepageFragmentDirections.actionHomepageFragmentToNotificationFragment()
                findNavController().navigate(action)
            }
            ivDateDeparture.setOnClickListener {
                showDatePickerDialog(requireContext(), etDateDeparture)
            }
            ivDateReturn.setOnClickListener {
                showDatePickerDialog(requireContext(), etDateReturn)
            }
            btnSearchFlight.setOnClickListener {
                val action = HomepageFragmentDirections.actionHomepageFragmentToFlightFragment(
                    seatClass = spSeatClass.selectedItem.toString(),
                    passenger = spPassenger.selectedItem.toString().toInt(),
                    airportDepart = departId.text.toString().toInt(),
                    airportArrival = arriveId.text.toString().toInt()
                )
                findNavController().navigate(action)
            }
            val passengerArray = arrayOf(1, 2, 3, 4, 5)
            homeSpinnerAdapter(array = passengerArray, spinner = spPassenger)
            val classArray = arrayOf("ECONOMY", "BUSINESS")
            homeSpinnerAdapter(classArray, spSeatClass)
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.airportList.collect {
                val content = it.data?.data?.content
                val res = it.data
                when(it){
                    is ApiResult.Loading -> binding.pbHome.visibility = View.VISIBLE
                    is ApiResult.Success -> {
                        binding.pbHome.visibility = View.GONE
                        val city = content?.map { it.city }?.toTypedArray() ?: emptyArray()
                        val cityId = content?.map { it.id.toString() }?.toTypedArray() ?: emptyArray()
                        departure(city) {
                            binding.departId.text = cityId[it]
                        }
                        arrival(city) {
                            binding.arriveId.text = cityId[it]
                        }
                        Log.d("neotica", "observeStateFlow: $content")
                    }
                    is ApiResult.Error -> {
                        binding.pbHome.visibility = View.GONE
                        Log.d("neotica", "observeStateFlow: Error")
                    }
                }
            }
        }
    }

    private fun departure(city: Array<String>, onItemSelected: (position: Int) -> Unit = {}){
        homeSpinnerAdapter(city, binding.spFlightFrom, onItemSelected = onItemSelected)
    }
    private fun arrival(city: Array<String>, onItemSelected: (position: Int) -> Unit = {}){
        homeSpinnerAdapter(city, binding.spFlightTo, onItemSelected = onItemSelected)
    }

    private fun homeSpinnerAdapter(array: Array<out Any>, spinner: Spinner, onItemSelected: (position: Int) -> Unit = {}){
        spinnerAdapter(array = array, spinner = spinner, context = requireContext(), onItemSelected = onItemSelected)
    }

    private fun tokenRan () {
        if (viewModel.token.isEmpty()) {
          //  gotoLogin()
        }
        Log.d("neotica", "token: ${viewModel.token}")
    }

    private fun gotoLogin() {
        val action = HomepageFragmentDirections.actionHomepageFragmentToAuthGroup()
        findNavController().navigate(action)
    }
}
