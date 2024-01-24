package com.synrgy.wefly.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.synrgy.wefly.R
import com.synrgy.wefly.common.repeatCollectionOnCreated
import com.synrgy.wefly.common.showDatePickerDialog
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
            ivDateDeparture.setOnClickListener {
                showDatePickerDialog(requireContext(), etDateDeparture)
            }
            ivDateReturn.setOnClickListener {
                showDatePickerDialog(requireContext(), etDateReturn)
            }
        }
    }

    private fun observeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.airportList.collect {
                val content = it.data?.data?.content
                when(it){
                    is ApiResult.Loading -> binding.pbHome.visibility = View.VISIBLE
                    is ApiResult.Success -> {
                        binding.pbHome.visibility = View.GONE
                        val city = content?.map { it.city }?.toTypedArray() ?: emptyArray()
                        departure(city)
                        arrival(city)
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

    private fun departure(city: Array<String>){
        flight(city, binding.spFlightFrom)
    }
    private fun arrival(city: Array<String>){
        flight(city, binding.spFlightTo)
    }

    private fun flight(arrayDemo: Array<String>, spinner: Spinner){
        with(binding) {
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayDemo)
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("neotica", arrayDemo[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("neotica", "Nothing is selected")
                }

            }
        }
    }

    private suspend fun tokenRan () {
        viewModel.token.collect {
            if (it.isEmpty()) {
                gotoLogin()
            }
            Log.d("neotica", "token: $it")
        }
    }

    private fun gotoLogin() {
        val action = HomepageFragmentDirections.actionHomepageFragmentToAuthGroup()
        findNavController().navigate(action)
    }
}
