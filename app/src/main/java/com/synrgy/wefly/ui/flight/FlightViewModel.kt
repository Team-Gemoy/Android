package com.synrgy.wefly.ui.flight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.flight.FlightListResponse
import com.synrgy.wefly.data.repository.FlightRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val repo: FlightRepositoryImpl
) : ViewModel() {

    private val _flightList: MutableStateFlow<ApiResult<HeaderResponse<FlightListResponse>>> =
        MutableStateFlow(ApiResult.Loading())

    val flightList: StateFlow<ApiResult<HeaderResponse<FlightListResponse>>> = _flightList
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading())


    fun getFlight(
        departDate: String,
        seatClass: String,
        numberOfPassenger: Int,
        departureAirportId: Int,
        arrivalAirportId: Int,
    ) = viewModelScope.launch {
        _flightList.value = ApiResult.Loading()
        _flightList.value = repo.getFlight(
            departureAirportId = departureAirportId,
            arrivalAirportId = arrivalAirportId,
            departDate = departDate,
            seatClass = seatClass,
            numberOfPassenger = numberOfPassenger
        )
    }
}