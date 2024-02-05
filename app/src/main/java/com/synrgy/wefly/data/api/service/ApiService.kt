package com.synrgy.wefly.data.api.service

import com.synrgy.wefly.data.api.HeaderResponse
import com.synrgy.wefly.data.api.json.flight.FlightListResponse
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
import retrofit2.http.Query

interface ApiService {
    @POST("forget-password/forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPassRequest): Call<ForgotPassResponse>

    @GET("flight/list")
    suspend fun getFlight(
      //  @Path("departDate") departDate: String
        @Query("departureAirportId") departureAirportId: Int,
        @Query("arrivalAirportId") arrivalAirportId: Int,
        @Query("departDate") departDate: String,
        @Query("seatClass") seatClass: String,
        @Query("numberOfPassenger") numberOfPassenger: Int
    ): HeaderResponse<FlightListResponse>

    @POST("transaction/save")
    fun saveTransaction(
        @Body transactionRequest: TransactionRequest,
    ): Call<HeaderResponse<TransactionResponseNew>>

    @GET("transaction/getById/{id}")
    suspend fun getTransactionId(
        @Path("id") id: String,
    ): HeaderResponse<TransactionListResponse>
}