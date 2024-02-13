package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.ContentResponse
import com.synrgy.wefly.data.api.json.flight.FlightContent

interface FlightRepository {
    suspend fun getFlight(
        departureAirportId: Int,
        arrivalAirportId: Int,
        departDate: String,
        seatClass: String,
        numberOfPassenger: Int
    ): ApiResult<HeaderResponse<ContentResponse<FlightContent>>>
}