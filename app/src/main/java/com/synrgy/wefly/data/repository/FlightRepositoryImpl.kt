package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.ApiService
import com.synrgy.wefly.data.api.flight.FlightListResponse
import com.synrgy.wefly.domain.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): FlightRepository {

    override suspend fun getFlight(): Flow<ApiResult<FlightListResponse>> = flow {
        emit(ApiResult.Loading())
        val response = apiService.getFlight()
        emit(ApiResult.Success(response))
    }.catch {
        emit(ApiResult.Error(it.message?: "Error Loading Flight list"))
    }

}