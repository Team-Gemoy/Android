package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.ContentResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.service.ApiService
import com.synrgy.wefly.domain.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): OrderRepository {
    override suspend fun getHistory(): ApiResult<HeaderResponse<ContentResponse<TransactionListResponse>>> = try {
        val response = apiService.getHistory()
        ApiResult.Success(response)
    } catch (e: Throwable) {
        ApiResult.Error(e.message ?: "Error get history")
    }
}