package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.json.airport.list.AirportListResponse
import com.synrgy.wefly.data.api.service.AuthService
import com.synrgy.wefly.domain.AirportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirportRepositoryImpl @Inject constructor(
    private val apiService: AuthService
): AirportRepository {
    override suspend fun getAirportList(): Flow<ApiResult<AirportListResponse>> = flow {
        emit(ApiResult.Loading())
        val response = apiService.getAirportList()
        emit(ApiResult.Success(response))
    }.catch {
        emit(ApiResult.Error(it.message?: "Error Loading Airport Lists"))
    }

}