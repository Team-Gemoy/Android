package com.synrgy.wefly.data.api.service

import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.data.api.json.forgetpassword.ForgotPassResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.json.transaction.TransactionRequest
import com.synrgy.wefly.data.api.json.transaction.TransactionResponseNew
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("forget-password/forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPassRequest): Call<ForgotPassResponse>



    @POST("transaction/save")
    fun saveTransaction(
        @Body transactionRequest: TransactionRequest,
    ): Call<HeaderResponse<TransactionResponseNew>>

    @GET("transaction/getById/{id}")
    suspend fun getTransactionId(
        @Path("id") id: String,
    ): HeaderResponse<TransactionListResponse>
}