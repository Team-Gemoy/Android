package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.transaction.TransactionRequest
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun saveTransaction(transactionRequest: TransactionRequest):
            Flow<ApiResult<HeaderResponse<TransactionListResponse>>>
}