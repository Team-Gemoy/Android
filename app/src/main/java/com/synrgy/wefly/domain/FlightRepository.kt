package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.flight.FlightListResponse
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    suspend fun getFlight(): Flow<ApiResult<FlightListResponse>>
}