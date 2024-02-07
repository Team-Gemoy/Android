package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.flight.FlightListResponse
import com.synrgy.wefly.data.api.service.AuthService
import com.synrgy.wefly.domain.FlightRepository
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor(
    private val apiService: AuthService
) : FlightRepository {

    override suspend fun getFlight(
        departureAirportId: Int,
        arrivalAirportId: Int,
        departDate: String,
        seatClass: String,
        numberOfPassenger: Int
    ): ApiResult<HeaderResponse<FlightListResponse>> = try {
        val response = apiService.getFlight(
            departureAirportId = departureAirportId,
            arrivalAirportId = arrivalAirportId,
            departDate = departDate,
            seatClass = seatClass,
            numberOfPassenger = numberOfPassenger
        )
        (ApiResult.Success(response))
    } catch (e: Throwable) {
        ApiResult.Error(e.message ?: "Error Loading Flight list")
    }

}