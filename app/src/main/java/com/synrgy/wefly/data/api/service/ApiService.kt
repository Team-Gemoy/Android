package com.synrgy.wefly.data.api.service

import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.ContentResponse
import com.synrgy.wefly.data.api.json.profile.ProfileResponse
import com.synrgy.wefly.data.api.json.register.AccountRequest
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionRequest
import com.synrgy.wefly.data.api.json.transaction.TransactionResponseNew
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("transaction/save")
    suspend fun saveTransaction(
        @Body transactionRequest: TransactionRequest,
    ): HeaderResponse<TransactionResponseNew>

    @GET("transaction/getById/{id}")
    suspend fun getTransactionId(
        @Path("id") id: String,
    ): HeaderResponse<TransactionListResponse>

    @GET("user/profile")
    suspend fun getUserProfile(): HeaderResponse<ProfileResponse>

    @GET("transaction/list?orderType=asc&orderBy=id")
    suspend fun getHistory(): HeaderResponse<ContentResponse<TransactionListResponse>>

    @PUT("user/update")
    suspend fun editProfile(
        @Body profileRequest: AccountRequest
    ): HeaderResponse<ProfileResponse>
}