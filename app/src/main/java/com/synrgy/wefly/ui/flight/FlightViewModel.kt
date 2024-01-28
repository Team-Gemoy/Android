package com.synrgy.wefly.ui.flight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.flight.FlightListResponse
import com.synrgy.wefly.data.repository.FlightRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val repo: FlightRepositoryImpl
) : ViewModel() {

    private val _flightList: MutableStateFlow<ApiResult<FlightListResponse>> =
        MutableStateFlow(ApiResult.Loading())

    val flightList: StateFlow<ApiResult<FlightListResponse>> = _flightList
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())



    fun getFlight(
        departDate: String,
        seatClass: String,
        numberOfPassenger: Int
    ) = viewModelScope.launch {
        repo.getFlight(
            departureAirportId = 2,
            arrivalAirportId = 1,
            departDate = departDate,
            seatClass = seatClass,
            numberOfPassenger = numberOfPassenger
        ).onStart { _flightList.value = ApiResult.Loading() }
            .collect {
            _flightList.value = it
        }
    }

}