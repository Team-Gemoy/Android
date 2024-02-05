package com.synrgy.wefly.domain

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionRequest
import com.synrgy.wefly.data.api.json.transaction.TransactionResponseNew
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun saveTransaction(transactionRequest: TransactionRequest):
            Flow<ApiResult<HeaderResponse<TransactionResponseNew>>>

    fun getTransaction(id: String): Flow<ApiResult<HeaderResponse<TransactionListResponse>>>
}