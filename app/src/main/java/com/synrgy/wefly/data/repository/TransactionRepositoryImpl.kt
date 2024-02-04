package com.synrgy.wefly.data.repository

import com.synrgy.wefly.data.api.ApiResult
import com.synrgy.wefly.data.api.ApiService
import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.transaction.TransactionRequest
import com.synrgy.wefly.domain.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private var apiService: ApiService
): TransactionRepository {
    override fun saveTransaction(transactionRequest: TransactionRequest): Flow<ApiResult<HeaderResponse<TransactionListResponse>>> =
        flow {
            emit(ApiResult.Loading())
            val request = apiService.saveTransaction(transactionRequest = transactionRequest).await()
            emit(ApiResult.Success(request))
        }.catch {
            emit(ApiResult.Error(it.message ?: "Register error"))
        }

    override fun getTransaction(id: String): Flow<ApiResult<HeaderResponse<TransactionListResponse>>> = flow {
        emit(ApiResult.Loading())
        val response = apiService.getTransactionId(id = id)
        emit(ApiResult.Success(response))
    }.catch { emit(ApiResult.Error(it.message ?: "Transaction Error")) }
}