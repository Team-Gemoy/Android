package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.ApiService
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.airport.list.AirportListResponse
import com.synrgy.wefly.data.api.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.transaction.TransactionRequest
import com.synrgy.wefly.domain.AirportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class AirportRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AirportRepository {
    override suspend fun getAirportList(): Flow<ApiResult<AirportListResponse>> = flow {
        emit(ApiResult.Loading())
        val response = apiService.getAirportList()
        emit(ApiResult.Success(response))
    }.catch {
        emit(ApiResult.Error(it.message?: "Error Loading Airport Lists"))
    }


    fun testTransaction(transactionRequest: TransactionRequest): Flow<ApiResult<HeaderResponse<TransactionListResponse>>> =
        flow<ApiResult<HeaderResponse<TransactionListResponse>>> {
            emit(ApiResult.Loading())
            val request = apiService.saveTransaction(transactionRequest = transactionRequest).await()
            emit(ApiResult.Success(request))
        }.catch {
            emit(ApiResult.Error(it.message ?: "Register error"))
        }
}