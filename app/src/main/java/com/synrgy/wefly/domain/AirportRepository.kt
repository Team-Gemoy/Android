package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.airport.list.AirportListResponse
import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    suspend fun getAirportList(): Flow<ApiResult<AirportListResponse>>
}