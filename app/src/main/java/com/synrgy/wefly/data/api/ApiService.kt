package com.synrgy.wefly.data.api

import com.synrgy.wefly.common.Constant.BEARER_TOKEN
import com.synrgy.wefly.data.api.airport.list.AirportListResponse
import com.synrgy.wefly.data.api.flight.FlightListResponse
import com.synrgy.wefly.data.api.forgetpassword.ForgotPassRequest
import com.synrgy.wefly.data.api.forgetpassword.ForgotPassResponse
import com.synrgy.wefly.data.api.login.LoginRequest
import com.synrgy.wefly.data.api.login.LoginResponse
import com.synrgy.wefly.data.api.register.RegisterRequest
import com.synrgy.wefly.data.api.register.RegisterResponse
import com.synrgy.wefly.data.api.transaction.TransactionListResponse
import com.synrgy.wefly.data.api.transaction.TransactionRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("user-login/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("user-register/register-user")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("forget-password/forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPassRequest): Call<ForgotPassResponse>

    //Airport
    @GET("airport/list")
    suspend fun getAirportList(): AirportListResponse

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
        @Header("Authorization") header: String? = BEARER_TOKEN
    ): Call<HeaderResponse<TransactionListResponse>>

    @GET("transaction/getById/{id}")
    suspend fun getTransactionId(
        @Path("id") id: String,
        @Header("Authorization") header: String
    ): HeaderResponse<TransactionListResponse>
}